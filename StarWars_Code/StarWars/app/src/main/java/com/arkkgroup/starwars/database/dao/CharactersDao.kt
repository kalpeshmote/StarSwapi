package com.arkkgroup.starwars.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.arkkgroup.starwars.entities.Character
import io.reactivex.Flowable


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Database Query holder for app
 */

@Dao
interface CharactersDao {

    @Query("SELECT * FROM viewmodel")
    fun loadAllCharacters(): Flowable<List<Character>>

    @Query("SELECT * FROM viewmodel WHERE page = :page")
    fun loadAllCharacters(page: String?): Flowable<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: MutableList<Character>) : Unit

}