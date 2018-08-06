package com.peterle.pedro.domain.repository

import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.domain.model.FilmDetails
import io.reactivex.Observable

interface SWAPIRepository {
    fun getAllFilms(): Observable<List<Film>>
    fun getFilmDetails(id: String): Observable<FilmDetails>
}