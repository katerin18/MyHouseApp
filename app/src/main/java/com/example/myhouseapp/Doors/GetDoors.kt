package com.example.myhouseapp.Doors

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.Doors.DataClassesDoors.DoorModel
import com.example.myhouseapp.Doors.DataClassesDoors.DoorsResponse
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText

private const val DOOR_URL = "https://cars.cprogroup.ru/api/rubetek/doors/"

class GetDoors {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getDoorsFromRequest(): List<DoorModel> {
        return try {
            val response = client.request(DOOR_URL)
            val parsedResponse = Gson().fromJson(response.bodyAsText(), DoorsResponse::class.java)
            parsedResponse.data
        } catch (e: Exception) {
            listOf()
        }
    }


}