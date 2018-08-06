package com.peterle.pedro.data

import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.domain.model.FilmDetails
import io.reactivex.Observable
import io.reactivex.Single

interface SWAPIRemote {

    fun getAllFilms(): Observable<List<Film>>

    fun getFilmDetails(id: String): Observable<FilmDetails>
}