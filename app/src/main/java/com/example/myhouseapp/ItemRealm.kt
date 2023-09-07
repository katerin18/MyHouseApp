package com.example.myhouseapp

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class ItemRealm: RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()
    var isDoor: Boolean? = null // camera/door
    var name: String? = null
    var snapshot: String? = null
    var room: String? = null
    var id: Int? = null
    var favorites: Boolean? = null
}