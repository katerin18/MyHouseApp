package com.example.myhouseapp.Cameras

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.ItemDataBase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CameraRepository : ViewModel() {
    private val responseCamera = GetCameras()
    private val config = RealmConfiguration.create(schema = setOf(ItemDataBase::class))
    private val realm: Realm = Realm.open(config)

    private val _cameras = MutableLiveData<RealmResults<ItemDataBase>>()
    val cameras: LiveData<RealmResults<ItemDataBase>> get() = _cameras
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

    fun getCameras() {
        val doors = realm.query<ItemDataBase>("isDoor != true").find()
    }
}