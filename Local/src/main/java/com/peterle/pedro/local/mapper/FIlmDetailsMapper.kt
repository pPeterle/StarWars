package com.peterle.pedro.local.mapper

import com.peterle.pedro.domain.model.FilmDetails
import com.peterle.pedro.local.model.FilmDetailsEntity

object FIlmDetailsMapper: Mapper<FilmDetailsEntity, FilmDetails> {

    override fun toDomain(entity: FilmDetailsEntity): FilmDetails {
        with(entity) {
            return FilmDetails(title, episodeId.toString(), releaseDate, characters, planets)
        }
    }

    override fun fromDomain(domain: FilmDetails): FilmDetailsEntity {
        with(domain) {
            return FilmDetailsEntity(title, episodeId.toInt(), releaseDate, characters, planets)
        }
    }
}