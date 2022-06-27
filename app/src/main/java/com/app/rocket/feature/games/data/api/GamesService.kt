package com.app.rocket.feature.games.data.api

import com.app.rocket.feature.games.data.models.GamesResponse
import retrofit2.http.GET

interface GamesService {

    @GET("/v3/covid-19/countries")
    suspend fun getCovidInformationByCountries(): List<GamesResponse>?
}
