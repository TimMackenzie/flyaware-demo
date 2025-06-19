package com.defenseunicorns.flyaware.data.network

import com.google.common.truth.Truth.assertThat
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

/**
 * Test with live API calls.  Note that overuse of this could cause throttling.
 */
class AviationWeatherApiLiveTest {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.NONE // only needed for debugging
        }
    }

    private val api = AviationWeatherApi(client)

    @Test
    fun `getMetar retrieves live METAR data`() = runBlocking {
        val metars = api.getMetar("KSFO")
        assertThat(metars).isNotEmpty()
        assertThat(metars.first().metarId).isNotNull()
        println("Live METAR: ${metars.first()}")
    }

    @Test
    fun `getMetar retrieves live METAR data for multiple sites`() = runBlocking {
        val metars = api.getMetar(listOf("KSFO", "KPHX"))
        assertThat(metars).isNotEmpty()
        assertThat(metars.first().metarId).isNotNull()
        assertThat(metars.size).isEqualTo(2)
        println("Live METAR: ${metars}")
    }

    @Test
    fun `getTaf retrieves live TAF data`() = runBlocking {
        val tafs = api.getTaf("KSFO")
        assertThat(tafs).isNotEmpty()
        assertThat(tafs.first().tafId).isNotNull()
        println("Live TAF: ${tafs.first()}")
    }
}