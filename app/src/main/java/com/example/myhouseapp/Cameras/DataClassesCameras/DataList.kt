package com.example.myhouseapp.Cameras.DataClassesCameras

import kotlinx.serialization.Serializable

@Serializable
data class DataList(
    val room: List<String>,
    val cameras: List<CameraModel>
)
