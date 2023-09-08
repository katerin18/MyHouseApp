package com.example.myhouseapp.Doors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.ItemDataBase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class DoorRepository : ViewModel() {
    private val responseDoor = GetDoors()
    private val config = RealmConfiguration.create(schema = setOf(ItemDataBase::class))
    private val realm: Realm = Realm.open(config)

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
}