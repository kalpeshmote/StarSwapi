package com.arkkgroup.starwars.database

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import com.arkkgroup.starwars.database.AppDatabase.Companion.DATABASE_NAME
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Kalpesh Mote on 5/26/2018.
 */

/**
 * This class Creates the [AppDatabase], exposing a LiveData object to notify of creation.
 */
object DatabaseCreator {

    private val isDatabaseCreated = MutableLiveData<Boolean>()

    lateinit var database: AppDatabase

    private val mInitializing = AtomicBoolean(true)

    fun createDb(context: Context) {
        if (mInitializing.compareAndSet(true, false).not()) {
            return
        }

        isDatabaseCreated.value = false

        Completable.fromAction {
            database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isDatabaseCreated.value = true }, {it.printStackTrace()})
    }

}