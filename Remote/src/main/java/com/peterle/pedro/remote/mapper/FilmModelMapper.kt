package com.peterle.pedro.remote.mapper

import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.remote.model.FilmModel

object FilmModelMapper: Mapper<FilmModel, Film> {
    override fun mapFromModel(model: FilmModel): Film =
            with(model) {
                Film(title, episodeId, releaseDate, characters)
            }

}