package com.defenseunicorns.flyaware.data.network

import android.util.Log
import com.defenseunicorns.flyaware.data.network.models.MetarData
import com.defenseunicorns.flyaware.data.network.models.TafData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AviationWeatherApi @Inject constructor(
    private val client: HttpClient
) {
    private val METAR_ENDPOINT = "https://aviationweather.gov/api/data/metar"
    private val TAF_ENDPOINT = "https://aviationweather.gov/api/data/taf"

    // Get metar data for a single ICAO
    suspend fun getMetar(icao: String): List<MetarData> {
        return client.get(METAR_ENDPOINT) {
            parameter("format", "json")
            parameter("ids", icao)
        }.body()
    }

    // Get metar data for a list of ICAOs
    suspend fun getMetar(icaoList: List<String>): List<MetarData> {
        Log.d("AviationWeatherApi", "requesting data for: ${icaoList.joinToString(",")}")
        val ids = icaoList.joinToString(",")
        return client.get(METAR_ENDPOINT) {
            parameter("format", "json")
            parameter("ids", ids)
        }.body()
    }

    suspend fun getTaf(icao: String): List<TafData> {
        return client.get(TAF_ENDPOINT) {
            parameter("format", "json")
            parameter("ids", icao)
        }.body()
    }
}