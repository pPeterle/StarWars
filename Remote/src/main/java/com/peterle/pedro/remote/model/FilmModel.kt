package com.peterle.pedro.remote.model

import com.google.gson.annotations.SerializedName

data class FilmModel(
        val title: String,
        @SerializedName("episode_id")
        val episodeId: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val characters: List<String>,
        val planets: List<String>
)