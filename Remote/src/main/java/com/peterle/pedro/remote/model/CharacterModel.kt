package com.peterle.pedro.remote.model

data class CharacterModel(
        val name: String,
        val gender: String,
        val films: List<FilmModel>
)