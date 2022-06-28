package com.app.rocket.feature.games.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.rocket.R
import com.app.rocket.databinding.ActivityGamesBinding
import com.app.rocket.feature.games.data.models.Game
import com.app.rocket.feature.games.ui.dialog.GameDialogFragment
import com.app.rocket.feature.games.ui.dialog.GameDialogFragment.Companion.GAME_DESC_ARGS
import com.app.rocket.feature.games.ui.dialog.GameDialogFragment.Companion.GAME_IMAGE_URL_ARGS
import com.app.rocket.feature.games.ui.dialog.GameDialogFragment.Companion.GAME_NAME_ARGS
import com.app.rocket.utils.EMPTY_STRING
import com.app.rocket.utils.viewBinding
import com.app.rocket.utils.visibleIfTrue
import dagger.hilt.android.AndroidEntryPoint

interface GameOnClickListener {
    fun onGameClicked(game: Game)
}

@AndroidEntryPoint
class GamesActivity : AppCompatActivity() {

    private lateinit var adapter: GameAdapter
    private lateinit var recyclerView: RecyclerView
    private val binding by viewBinding(ActivityGamesBinding::inflate)
    private val viewModel: GamesViewModel by viewModels()

    private val gameOnClickListener = object : GameOnClickListener {
        override fun onGameClicked(game: Game) {
            var gameDescriptionReplaceHtml = EMPTY_STRING
            if (!game.description.isNullOrEmpty()) {
                gameDescriptionReplaceHtml = HtmlCompat.fromHtml(game.description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            }
            GameDialogFragment.getInstance().apply {
                val b = Bundle()
                b.putString(GAME_IMAGE_URL_ARGS, game.image?.icon_url ?: EMPTY_STRING)
                b.putString(GAME_NAME_ARGS, game.name ?: EMPTY_STRING)
                b.putString(GAME_DESC_ARGS, gameDescriptionReplaceHtml)

                arguments = b

                show(supportFragmentManager, GameDialogFragment.TAG)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            lifecycleOwner = this@GamesActivity
            gamesViewModel = viewModel
        }

        adapter = GameAdapter( { game -> gameOnClickListener.onGameClicked(game) } , resources)
        recyclerView = binding.gamesRv
        recyclerView.adapter = adapter

        initObservers()
    }

    private fun initObservers() {
        with(viewModel) {
            gamesResponse.observe(this@GamesActivity) { resp ->
               adapter.updateAdapter(resp.data ?: listOf())
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
                    errorLoading.apply {
                        visibleIfTrue(isError)
                        text = getString(R.string.error_loading)
                    }
                }
            }
        }
    }
}
