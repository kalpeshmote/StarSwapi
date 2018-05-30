package com.arkkgroup.starwars.viewmodel

import android.arch.lifecycle.MediatorLiveData
import com.arkkgroup.starwars.entities.Character
import com.arkkgroup.starwars.sources.characters.CharactersRepository
import io.reactivex.disposables.Disposable


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * This Class is core Functional Component. Holds all the reference for
 * ViewDataModel, and Lifecycle.
 */

class CharactersLiveData : MediatorLiveData<Pair<List<Character>?, Throwable?>>() {

    private var disposable: Disposable? = null


    //To holde page number
    var page: String? = null
        set(value) {
            value?.let {
                disposable = CharactersRepository
                        .getCharacters(it)
                        .subscribe { data, error -> this@CharactersLiveData.value = Pair(data, error)}

            }
        }

    /**
     * Lcall back for liveData inactive.
     */
    override fun onInactive() {
        super.onInactive()
        if (disposable?.isDisposed?.not() ?: false) {
            disposable?.dispose()
        }
    }

}
