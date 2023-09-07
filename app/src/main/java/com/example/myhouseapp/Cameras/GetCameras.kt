package com.example.myhouseapp.Cameras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.Cameras.DataClassesCameras.CameraModel
import com.example.myhouseapp.Cameras.DataClassesCameras.CamerasResponse
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

private const val CAMERA_URL = "https://cars.cprogroup.ru/api/rubetek/cameras/"

class GetCameras : ViewModel() {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private suspend fun getCamerasFromRequest(): List<CameraModel> {
        return try {
            val response = client.get(CAMERA_URL)
            val parsedResponseBody =
                Gson().fromJson(response.bodyAsText(), CamerasResponse::class.java)
            parsedResponseBody.data.cameras
        } catch (e: Exception) {
            listOf()
        }

    }

    fun getResponseCameras() {
        viewModelScope.launch {
            getCamerasFromRequest()
        }
    }

}