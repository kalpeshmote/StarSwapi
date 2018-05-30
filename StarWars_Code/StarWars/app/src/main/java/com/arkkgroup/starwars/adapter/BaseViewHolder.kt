package com.arkkgroup.starwars.adapter

import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Abstract class to retain ViewHolder.
 */

abstract class BaseViewHolder<D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: D)

}