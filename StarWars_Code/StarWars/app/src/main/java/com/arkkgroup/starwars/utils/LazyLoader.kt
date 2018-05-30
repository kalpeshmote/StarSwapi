package com.arkkgroup.starwars.utils

/**
 * Created by Kalpesh Mote on 5/26/2018.
 */

/**
 * A lazy function.
 *  To initializer for LiveData
 */
fun <T> lazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
