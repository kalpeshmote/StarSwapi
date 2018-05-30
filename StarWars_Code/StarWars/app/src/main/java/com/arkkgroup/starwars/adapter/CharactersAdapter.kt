package com.arkkgroup.starwars.adapter

import android.view.View
import android.widget.TextView
import com.arkkgroup.starwars.R
import com.arkkgroup.starwars.entities.Character


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * This Class represents Adapter for characters Recycler View
 */

class CharactersAdapter(private val itemClick: (Character) -> Unit) : BaseAdapter<Character, CharactersAdapter.CharactersViewHolder>() {

    override fun getItemViewId(): Int = R.layout.view_item

    override fun instantiateViewHolder(view: View?): CharactersViewHolder = CharactersViewHolder(view, itemClick)

    class CharactersViewHolder(itemView: View? , private val itemClick: (Character) -> Unit) : BaseViewHolder<Character>(itemView) {
        val tvName by lazy { itemView?.findViewById<TextView?>(R.id.tvName) }
        override fun onBind(item: Character) {
            tvName?.text = item.name
            itemView.setOnClickListener { itemClick(item) }
        }

    }

}