package com.example.myhouseapp.Cameras.DataClassesCameras

import kotlinx.serialization.Serializable

@Serializable
data class CamerasResponse(
    val success: Boolean,
    val data: DataList
)
