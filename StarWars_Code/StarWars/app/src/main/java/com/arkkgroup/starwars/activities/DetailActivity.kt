package com.arkkgroup.starwars.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.arkkgroup.starwars.R
import com.arkkgroup.starwars.application.ToolbarManager
import com.arkkgroup.starwars.viewmodel.CharactersViewModel
import com.arkkgroup.starwars.utils.Constants.*
import com.arkkgroup.starwars.utils.Utility.Companion.getMetersFromCM
import com.arkkgroup.starwars.utils.Utility.Companion.processDate
import kotlinx.android.synthetic.main.activity_details.*
import org.jetbrains.anko.find


/**
 * Created by Kalpesh Mote on 5/26/2018.
 */
/**
 * Class is responsible for displaying Details of Character Selected.
 */


class DetailActivity : BaseLifecycleActivity<CharactersViewModel>(), ToolbarManager {


    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override val viewModelClass = CharactersViewModel::class.java

    /** Component of the activity are declared
     *
     */
    companion object {
        const val NAME = "DetailActivity:name"
        const val HEIGHT = "DetailActivity:height"
        const val MASS = "DetailActivity:mass"
        const val CREATED = "DetailActivity:created"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        iniUI();
    }


    /** Method to Load the UI Component
     *
     */
    fun iniUI() {
        toolbarTitle = intent.getStringExtra(NAME)
        enableHomeAsUp { onBackPressed() }
        mName.setContentText(intent.getStringExtra(NAME))
        mHeight.setContentText(getMetersFromCM(Integer.valueOf(intent.getStringExtra(HEIGHT))) + METRES_TEXT)
        mMass.setContentText(intent.getStringExtra(MASS) + KG_TEXT)
        val dateRcvd = intent.getStringExtra(CREATED);
        mDateCreated.setContentText(processDate(dateRcvd))
    }

    /** Method to refresh characters list
     *
     */
    override fun refreshData() {
    }

}


