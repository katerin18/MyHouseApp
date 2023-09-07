package com.example.myhouseapp.Doors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouseapp.Doors.DataClassesDoors.DoorModel
import com.example.myhouseapp.ItemRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.launch

class DoorRepository : ViewModel() {
    val responseDoor = GetDoors()
    val config = RealmConfiguration.create(schema = setOf(ItemRealm::class))
    val realm: Realm = Realm.open(config)

    fun saveDoors() {
        viewModelScope.launch {
            for (i in responseDoor.getDoorsFromRequest()) {
                realm.writeBlocking {
                    copyToRealm(ItemRealm().apply {
                        isDoor = true
                        name = i.name
                        snapshot = i.snapshot
                        room = i.room
                        id = i.id
                        favorites = i.favorites
                    })
                }
            }
        }
    }
}