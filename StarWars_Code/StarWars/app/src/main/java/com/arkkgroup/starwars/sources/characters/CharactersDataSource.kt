package com.arkkgroup.starwars.sources.characters

import com.arkkgroup.starwars.entities.Character
import io.reactivex.Single

/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Class Represents Data Source for Character Object
 */

interface CharactersDataSource {

    fun getCharacters(page: String): Single<List<Character>>

    fun saveCharacters(list: List<Character>) : Unit = Unit

}