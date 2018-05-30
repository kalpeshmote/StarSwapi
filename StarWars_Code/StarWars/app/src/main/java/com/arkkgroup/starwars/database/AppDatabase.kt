package com.arkkgroup.starwars.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.arkkgroup.starwars.entities.Character
import com.arkkgroup.starwars.database.dao.CharactersDao

/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * App Database instance forr Star wars app.
 */

@Database(entities = arrayOf(Character::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {
        const val DATABASE_NAME = "star-wars-db"
    }

}