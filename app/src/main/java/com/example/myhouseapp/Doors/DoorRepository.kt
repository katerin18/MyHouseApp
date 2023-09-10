package com.example.myhouseapp.Doors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.ItemDataBase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class DoorRepository : ViewModel() {
    private val responseDoor = GetDoors()
    private val config = RealmConfiguration.create(schema = setOf(ItemDataBase::class))
    private val realm: Realm = Realm.open(config)
    val allDoors = MutableStateFlow<List<ItemDataBase>>(listOf())

    fun saveDoors() {
        viewModelScope.launch {
            val listDoor = responseDoor.getDoorsFromRequest()
            for (i in listDoor.indices) {
                realm.writeBlocking {
                    copyToRealm(ItemDataBase().apply {
                        _id = ObjectId()
                        isDoor = true
                        name = listDoor[i].name
                        snapshot = listDoor[i].snapshot
                        room = listDoor[i].room
                        id = listDoor[i].id
                        favorites = listDoor[i].favorites
                    })
                }
            }
        }
    }

    fun getDoors(): StateFlow<List<ItemDataBase>> {
        val doors = realm.query<ItemDataBase>("isDoor == true").find()
        allDoors.value = realm.copyFromRealm(doors)

        return allDoors.asStateFlow()
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}