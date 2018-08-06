package com.peterle.pedro.domain.model

data class Film(
        val title: String,
        var episodeId: String,
        val releaseDate: String,
        val characters: List<String>
)