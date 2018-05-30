package com.arkkgroup.starwars.customviews

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.arkkgroup.starwars.R
import kotlinx.android.synthetic.main.row_card.view.*


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * Custom view to for outer card on Details page
 */

class DetailsPaperLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {
    init {

        LayoutInflater.from(context).inflate(R.layout.row_card, this, true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.DetailsPaperLayout)
        val title = a.getString(R.styleable.DetailsPaperLayout_title)
        if (!TextUtils.isEmpty(title)) {
            mTitleTextView.text = title
        }
        a.recycle()
    }
    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (mCardContent != null) {
            mCardContent.addView(child, index, params)
        } else {
            super.addView(child, index, params)
        }
    }
}