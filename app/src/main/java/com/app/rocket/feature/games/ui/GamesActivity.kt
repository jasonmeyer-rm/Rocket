package com.app.rocket.feature.games.ui

import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.rocket.databinding.ActivityGamesBinding
import com.app.rocket.feature.games.data.models.Game
import com.app.rocket.utils.showToast
import com.app.rocket.utils.viewBinding
import com.app.rocket.utils.visibleIfTrue
import dagger.hilt.android.AndroidEntryPoint

interface GamesListOnClickListener {
    fun onGamesItemClicked(results: Game)
}


@AndroidEntryPoint
class GamesActivity : AppCompatActivity() {

    //private lateinit var adapter: GamesAdapter
    private lateinit var recyclerView: RecyclerView

    private val binding by viewBinding(ActivityGamesBinding::inflate)
    private val viewModel: GamesViewModel by viewModels()

    private val gameListOnClickListener = object : GamesListOnClickListener {
        override fun onGamesItemClicked(results: Game) {
            showToast(this@GamesActivity, results.description)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            lifecycleOwner = this@GamesActivity
            gamesViewModel = viewModel
        }

        //adapter = GamesAdapter( { game -> gameListOnClickListener.onCovidCardClicked(country) } , resources)
        recyclerView = binding.gamesRv
        //recyclerView.adapter = adapter

        initObservers()

    }

    private fun initObservers() {
        with(viewModel) {
            gamesResponse.observe(this@GamesActivity) { resp ->
               // adapter.updateAdapter(resp.data ?: listOf())
                showToast(this@GamesActivity, resp.toString())
            }
            isLoading.observe(this@GamesActivity) { isLoading ->
                with(binding) {
                    progressBar.visibleIfTrue(isLoading)
                    gamesRv.visibleIfTrue(!isLoading)
                }
            }
            isError.observe(this@GamesActivity) { isError ->
                with(binding) {
                    progressBar.visibleIfTrue(!isError)
                    gamesRv.visibleIfTrue(!isError)
                }
            }
        }
    }
}