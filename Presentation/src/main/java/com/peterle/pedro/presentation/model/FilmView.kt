package com.peterle.pedro.presentation.model

import android.os.Parcelable

data class FilmView(
        val title: String,
        val episodeId: String,
        val releaseDate: String,
        val characters: List<String>,
        var image: Int = 0
)