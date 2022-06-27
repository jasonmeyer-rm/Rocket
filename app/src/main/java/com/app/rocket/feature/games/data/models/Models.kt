package com.app.rocket.feature.games.data.models

data class GamesResponse(
    val country: String = "",
    val countryInfo: CountryInfo = CountryInfo(flag = ""),
    val cases: Int = 0,
    val todayCases: Int = 0,
    val deaths: Int = 0,
    val todayDeaths: Int = 0,
    val recovered: Int = 0,
    val active: Int = 0,
    val tests: Int = 0,
    val population: Int = 0,
    val continent: String = "",
    val critical: Int = 0,
    val oneCasePerPeople: Int = 0,
    val oneTestPerPeople: Int = 0
)

data class CountryInfo(val flag: String)