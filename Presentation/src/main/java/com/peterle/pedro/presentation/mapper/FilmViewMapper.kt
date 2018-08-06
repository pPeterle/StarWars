package com.peterle.pedro.presentation.mapper

import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.presentation.model.FilmView

object FilmViewMapper: Mapper<Film, FilmView> {

    override fun maptToView(model: Film): FilmView =
            with(model) {
                FilmView(title, episodeId, releaseDate, characters)
            }


}