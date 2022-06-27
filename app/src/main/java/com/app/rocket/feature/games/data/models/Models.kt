package com.app.rocket.feature.games.data.models

data class GamesResponse(val results: List<Game>)

data class Game(
    val name: String = "",
    val description: String = "",
    val image: Image = Image(icon_url = "")
)

data class Image(val icon_url: String)
