package com.app.rocket.feature.games.data.api

import com.app.rocket.BuildConfig.GAMES_API_KEY
import com.app.rocket.feature.games.data.models.GamesResponse
import retrofit2.http.GET

interface GamesService {

    @GET("api/games/?api_key=$GAMES_API_KEY&format=json")
    suspend fun getGames(): GamesResponse
}
