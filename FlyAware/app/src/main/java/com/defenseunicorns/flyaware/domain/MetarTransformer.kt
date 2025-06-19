package com.defenseunicorns.flyaware.domain

import android.util.Log
import com.defenseunicorns.flyaware.data.network.models.MetarData
import com.defenseunicorns.flyaware.model.*
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.inject.Inject

class MetarTransformer @Inject constructor() {
    fun transform(data: MetarData): Metar {
        Log.d("transformer", "transforming $data")
        return Metar(
            icaoCode = data.icaoId,
            rawText = data.rawObservation ?: "",
            observationTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(data.obsTime), ZoneOffset.UTC),
            temperature = data.temp,
            dewpoint = data.dewp,
            windDirection = data.wdir,
            windSpeed = data.wspd,
            windGust = data.wgst,
            visibility = parseVisibility(data.visibility),
            altimeter = data.altim,
            flightCategory = FlightCategory.UNKNOWN, // TODO Source of this data needs to be found
            cloudLayers = data.clouds?.map {
                CloudLayer(
                    coverage = try {
                        CloudCoverage.valueOf(it.cover ?: "UNKNOWN")
                    } catch (e: IllegalArgumentException) {
                        CloudCoverage.UNKNOWN
                    },
                    baseAltitudeFeet = it.base,
//                    cloudType = it.type
                )
            } ?: emptyList(),
            weatherConditions = emptyList() // Populate if available
        )
    }

    private fun parseVisibility(vis: String?): Double? {
        return vis?.replace("+", "")?.toDoubleOrNull()
    }
}