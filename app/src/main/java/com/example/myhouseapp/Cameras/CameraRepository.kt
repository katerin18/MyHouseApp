package com.example.myhouseapp.Cameras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.ItemDataBase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CameraRepository: ViewModel() {
    private val responseCamera = GetCameras()
    private val config = RealmConfiguration.create(schema = setOf(ItemDataBase::class))
    private val realm: Realm = Realm.open(config)

    fun saveCameras() {
        viewModelScope.launch {
            val listCamera = responseCamera.getCamerasFromRequest()
            for (i in listCamera.indices) {
                realm.writeBlocking {
                    copyToRealm(ItemDataBase().apply {
                        _id = ObjectId()
                        isDoor = false
                        name = listCamera[i].name
                        snapshot = listCamera[i].snapshot
                        room = listCamera[i].room
                        id = listCamera[i].id
                        favorites = listCamera[i].favorites
                    })
                }
            }
        }
    }
}