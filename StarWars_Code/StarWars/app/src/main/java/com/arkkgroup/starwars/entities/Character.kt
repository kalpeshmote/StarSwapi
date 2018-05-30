package com.arkkgroup.starwars.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Entity class to hold Character properties
 */

@Entity(tableName = "viewmodel")
data class Character (
        @PrimaryKey var id: Long?,
        var name: String?,
        var height: String?,
        var mass: String?,
        var created: String?,
        var page: String?)