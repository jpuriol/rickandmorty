package com.example.rickandmorty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object API {
    private const val BASE_URL = "https://rickandmortyapi.com/api"
    private val client = HttpClient(CIO)

    suspend fun getCharacters(page: Int = 1): CharactersResponse {
        val response = client.get("$BASE_URL/character?page=$page")

        if (response.status != HttpStatusCode.OK) {
            error("Invalid response status ${response.status}")
        }

        val body: String = response.bodyAsText()
        return Json.decodeFromString(body)

    }

    fun cleanup() {
        client.close()
    }

}