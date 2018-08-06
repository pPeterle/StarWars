package com.peterle.pedro.remote.model

import com.google.gson.annotations.SerializedName
import com.peterle.pedro.domain.model.Planet

data class FilmDetailsModel(
        val title: String,
        @SerializedName("episode_id")
        val episodeId: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val characters: List<PeopleModel>,
        val planets: List<Planet>
)