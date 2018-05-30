package com.arkkgroup.starwars.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.arkkgroup.starwars.entities.Character


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * ViewModel Class to Hold Livedata transaction references
 */

open class CharactersViewModel(application: Application?) : AndroidViewModel(application) {

    private val pageLiveData = MutableLiveData<String>()

    val resultLiveData = CharactersLiveData().apply {
        this.addSource(pageLiveData) { it?.let { this.page = it } }
    }

    val isLoadingLiveData = MediatorLiveData<Boolean>().apply {
        this.addSource(resultLiveData) { this.value = false }
    }

    val throwableLiveData = MediatorLiveData<Throwable>().apply {
        this.addSource(resultLiveData) { it?.second?.let { this.value = it } }
    }

    val charactersLiveData = MediatorLiveData<List<Character>>().apply {
        this.addSource(resultLiveData) { it?.first?.let { this.value = it } }
    }

    fun setPage(page: String) {
        pageLiveData.value = page
        isLoadingLiveData.value = true
    }

}