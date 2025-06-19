package com.defenseunicorns.flyaware.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TafData(
    @SerialName("tafId") val tafId: Long,
    @SerialName("icaoId") val icaoId: String,
    @SerialName("dbPopTime") val dbPopTime: String,
    @SerialName("bulletinTime") val bulletinTime: String,
    @SerialName("issueTime") val issueTime: String,
    @SerialName("validTimeFrom") val validTimeFrom: Long,
    @SerialName("validTimeTo") val validTimeTo: Long,
    @SerialName("rawTAF") val rawTaf: String,
    @SerialName("mostRecent") val mostRecent: Int,
    val remarks: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val elev: Int? = null,
    val prior: Int? = null,
    val name: String? = null,
    val fcsts: List<TafForecast>? = null
)

@Serializable
data class TafForecast(
    val timeGroup: Int,
    val timeFrom: Long,
    val timeTo: Long,
    val timeBec: Long? = null,
    val fcstChange: String? = null,
    val probability: Int? = null,
    val wdir: Int? = null,
    val wspd: Int? = null,
    val wgst: Int? = null,
    val wshearHgt: Int? = null,
    val wshearDir: Int? = null,
    val wshearSpd: Int? = null,
    val visib: String? = null,
    val altim: Double? = null,
    val vertVis: Int? = null,
    val wxString: String? = null,
    val notDecoded: String? = null,
    val clouds: List<TafCloud>? = null,
    val icgTurb: List<String>? = null,
    val temp: List<String>? = null
)

@Serializable
data class TafCloud(
    val cover: String? = null,
    val base: Int? = null,
    val type: String? = null
)