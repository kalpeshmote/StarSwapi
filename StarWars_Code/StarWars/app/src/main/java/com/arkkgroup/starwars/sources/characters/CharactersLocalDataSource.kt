package com.arkkgroup.starwars.sources.characters

import com.arkkgroup.starwars.entities.Character
import com.arkkgroup.starwars.database.DatabaseCreator
import io.reactivex.Single

/**
 * Created by Kalpesh Mote on 5/26/2018.
 */

object CharactersLocalDataSource : CharactersDataSource {

   private val charactersDao = DatabaseCreator.database.charactersDao()

    /**
     * Load Character from service
     */

    override fun getCharacters(page: String): Single<List<Character>>
        = charactersDao
                .loadAllCharacters(page)
                .firstOrError()
                .doOnSuccess { if (it.isEmpty()) throw Exception() }


    /**
     * Save Character from service
     */
    override fun saveCharacters(list: List<Character>)
        =  charactersDao.insertAll(list.toMutableList())

}