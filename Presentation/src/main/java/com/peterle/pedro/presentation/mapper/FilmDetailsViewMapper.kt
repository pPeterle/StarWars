package com.peterle.pedro.presentation.mapper

import com.peterle.pedro.domain.model.FilmDetails
import com.peterle.pedro.presentation.model.FilmDetailsView

object FilmDetailsViewMapper: Mapper<FilmDetails, FilmDetailsView> {
    override fun maptToView(model: FilmDetails): FilmDetailsView {
        with(model) {
            return FilmDetailsView(title, episodeId, releaseDate, characters.map { PeopleViewMapper.maptToView(it) }, planets.map { PlanetViewMapper.maptToView(it) })
        }
    }
}