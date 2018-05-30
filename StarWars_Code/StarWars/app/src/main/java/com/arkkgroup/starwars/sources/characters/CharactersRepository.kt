package com.arkkgroup.starwars.sources.characters

import com.arkkgroup.starwars.entities.Character
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * This Class Calls Character Datasource to load Character Properties
 */
object CharactersRepository : CharactersDataSource {

    /**
     * Load Character from service
     */
    override fun getCharacters(page: String): Single<List<Character>>
        = CharactersLocalDataSource
                .getCharacters(page)
                .onErrorResumeNext {
                    CharactersRemoteDataSource.getCharacters(page)
                            .doOnSuccess { CharactersLocalDataSource.saveCharacters(it) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
}