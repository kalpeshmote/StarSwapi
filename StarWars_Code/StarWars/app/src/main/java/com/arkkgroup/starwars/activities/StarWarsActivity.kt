package com.arkkgroup.starwars.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.arkkgroup.starwars.R
import com.arkkgroup.starwars.application.ToolbarManager
import com.arkkgroup.starwars.adapter.CharactersAdapter
import com.arkkgroup.starwars.viewmodel.CharactersViewModel
import com.arkkgroup.starwars.entities.Character
import com.arkkgroup.starwars.utils.Constants.NETWORK_ERROR_TEXT
import com.arkkgroup.starwars.utils.Constants.REFRESH_TEXT
import com.arkkgroup.starwars.utils.lazy
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


/**
 * Created by Kalpesh Mote on 5/26/2018.
 *
 * This is the Main Launcher Activity of the application.
 */

class StarWarsActivity : BaseLifecycleActivity<CharactersViewModel>(), SwipeRefreshLayout.OnRefreshListener, ToolbarManager {
    override fun refreshData() {
        viewModel.setPage(REFRESH_TEXT)
    }

    override val viewModelClass = CharactersViewModel::class.java

    private val rv by lazy { findViewById<RecyclerView>(R.id.rv) }

    private val vRefresh by lazy { findViewById<SwipeRefreshLayout>(R.id.lRefresh) }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stars)
        if (savedInstanceState == null) {
            viewModel.setPage(REFRESH_TEXT)
        }
        initUI()

    }


    /** Method to Load the UI Component
     *
     */
    fun initUI() {

        initToolbar()
        toolbarTitle = getString(R.string.app_name)
        rv.layoutManager = LinearLayoutManager(this)

        rv.setHasFixedSize(true)

        val adapter = CharactersAdapter() {
            startActivity<DetailActivity>(
                    DetailActivity.NAME to it.name, DetailActivity.MASS to it.mass, DetailActivity.HEIGHT to it.height, DetailActivity.CREATED to it.created)
        }

        rv.adapter = adapter
        vRefresh.setOnRefreshListener(this)
        observeLiveData(adapter)

    }



    /** Function observes livedata instance
     *
     */
    private fun observeLiveData(adapter: CharactersAdapter) {
        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
            it?.let { vRefresh.isRefreshing = it }
        })
        viewModel.charactersLiveData.observe(this, Observer<List<Character>> {
            it?.let { adapter.dataSource = it }
        })
        viewModel.throwableLiveData.observe(this, Observer<Throwable> {
            it?.let {
                Snackbar.make(
                        rv, // Parent view
                        NETWORK_ERROR_TEXT, // Message to show
                        Snackbar.LENGTH_LONG //
                ).setAction( // Set an action for snack bar
                        R.string.retry, // Action button text
                        {
                            // Action button click listener
                            // Do something when undo action button clicked
                            viewModel.setPage(REFRESH_TEXT)
                        }).show()


            }
        })
    }

    /** Method to refresh characters list
     *
     */
    override fun onRefresh() {
        viewModel.setPage(REFRESH_TEXT)
    }
}
