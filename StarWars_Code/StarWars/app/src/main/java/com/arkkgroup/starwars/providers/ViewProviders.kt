package com.arkkgroup.starwars.providers

import android.content.Context
import android.view.View
import android.widget.TextView


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Extension class to hold reference for view properties.
 */


val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)