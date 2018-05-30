package com.arkkgroup.starwars.application

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.Toolbar

import com.arkkgroup.starwars.R
import com.arkkgroup.starwars.providers.ctx
import com.arkkgroup.starwars.utils.Constants.UNKNOWN_OPTION
import org.jetbrains.anko.toast

/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * ToolbarManager Class Holds reference for Toolbar of the application
 */

interface ToolbarManager {

    val toolbar: Toolbar

    //To set Toolbar title
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }


    /** Method to initialise toolbar with Menu and other items
     *
     */
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_refresh -> refreshData()
                else -> App.instance.toast(UNKNOWN_OPTION)
            }
            true
        }
    }

    /** Method to enable back arrow icon on toolbar
     *
     */
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    /** Method to create drawale item.
     *
     */
    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }


    /** Method to refresh characters list
     *
     */
    fun refreshData()

}