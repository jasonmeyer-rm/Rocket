package com.app.rocket.feature.games.repository

import com.app.rocket.feature.games.data.api.GamesService
import javax.inject.Inject

class GamesRepository @Inject constructor(private val gamesService: GamesService) {
    suspend fun getGames() = gamesService.getGames()
}
