package com.example.myhouseapp.Cameras.DataClassesCameras

import kotlinx.serialization.Serializable

@Serializable
data class CameraModel(
    val name: String? = null,
    val snapshot: String? = null,
    val room: String? = null,
    val id: Int? = null,
    val favorites: Boolean? = null,
    val rec: Boolean? = null
)
