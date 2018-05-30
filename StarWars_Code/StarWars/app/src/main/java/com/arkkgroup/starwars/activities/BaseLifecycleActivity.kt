package com.arkkgroup.starwars.activities

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.arkkgroup.starwars.utils.lazy


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * This is base abstract class to hold reference for MVVM components
 */

@Suppress("LeakingThis")
abstract class BaseLifecycleActivity<T : AndroidViewModel> : AppCompatActivity() {

    abstract val viewModelClass: Class<T>

    protected val viewModel: T by lazy { ViewModelProviders.of(this).get(viewModelClass) }

    private val registry = LifecycleRegistry(this)

    //To get the lifecycle from Registry
    override fun getLifecycle(): LifecycleRegistry = registry
}
