package com.arkkgroup.starwars.customviews

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.arkkgroup.starwars.R
import kotlinx.android.synthetic.main.item_row_card.view.*


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Custom view to Hold Details card information
 */

class DetailRowLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_row_card, this, true)
        val a = context.obtainStyledAttributes(attrs, R.styleable.DetailRowLayout)

        val title = a.getString(R.styleable.DetailRowLayout_title)
        if (!TextUtils.isEmpty(title)) {
            titleTextView.text = title
        }
        a.recycle()
    }

    /** Method to set data value.
     *
     */

    fun setContentText(text: CharSequence) {
        contentTextView.text = text
    }
}