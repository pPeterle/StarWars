package com.peterle.pedro.presentation.model

data class FilmDetailsView(
        val title: String,
        val episodeId: String,
        val releaseDate: String,
        val characters: List<PeopleView>,
        val planets: List<PlanetView>,
        var image: Int = 0
)