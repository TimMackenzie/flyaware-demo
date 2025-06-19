package com.defenseunicorns.flyaware.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetarData(
    @SerialName("metar_id") val metarId: Long,
    @SerialName("icaoId") val icaoId: String,
    @SerialName("receiptTime") val receiptTime: String,
    @SerialName("obsTime") val obsTime: Long,
    @SerialName("reportTime") val reportTime: String,
    val temp: Double? = null,
    val dewp: Double? = null,
    val wdir: Int? = null,
    val wspd: Int? = null,
    val wgst: Int? = null,
    @SerialName("visib") val visibility: String? = null,
    val altim: Double? = null,
    val slp: Double? = null,
    val qcField: Int? = null,
    val wxString: String? = null,
    val presTend: Double? = null,
    val maxT: Double? = null,
    val minT: Double? = null,
    val maxT24: Double? = null,
    val minT24: Double? = null,
    val precip: Double? = null,
    val pcp3hr: Double? = null,
    val pcp6hr: Double? = null,
    val pcp24hr: Double? = null,
    val snow: Double? = null,
    val vertVis: Double? = null,
    val metarType: String? = null,
    @SerialName("rawOb") val rawObservation: String? = null,
    val mostRecent: Int? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val elev: Int? = null,
    val prior: Int? = null,
    val name: String? = null,
    val clouds: List<CloudLayer>? = null
)

@Serializable
data class CloudLayer(
    val cover: String? = null,
    val base: Int? = null
)
