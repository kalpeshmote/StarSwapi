package com.arkkgroup.starwars.application

import android.app.Application
import com.arkkgroup.starwars.database.DatabaseCreator
import com.arkkgroup.starwars.providers.DelegatesExt


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * This is the Base Application Class of StarWars App
 */

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Database instance created
        DatabaseCreator.createDb(this)
    }
}