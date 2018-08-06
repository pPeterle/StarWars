package com.peterle.pedro.data

import com.peterle.pedro.domain.model.FilmDetails
import io.reactivex.Maybe
import io.reactivex.Observable

interface SWAPILocal {
    fun insertFilmDetails(filmDetails: FilmDetails)

    fun getAllFilmsDetails(id: String): Maybe<FilmDetails>
}