package com.peterle.pedro.domain.model

data class FilmDetails(
        val title: String,
        val episodeId: String,
        val releaseDate: String,
        val characters: List<People>,
        val planets: List<Planet>
)