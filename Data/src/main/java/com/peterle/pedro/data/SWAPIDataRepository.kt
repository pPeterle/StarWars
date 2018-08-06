package com.peterle.pedro.data

import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.domain.model.FilmDetails
import com.peterle.pedro.domain.repository.SWAPIRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SWAPIDataRepository @Inject constructor(private val remote: SWAPIRemote, private val local: SWAPILocal): SWAPIRepository {

    override fun getFilmDetails(id: String): Observable<FilmDetails> {
         return local.getAllFilmsDetails(id).toObservable().switchIfEmpty(remote.getFilmDetails(id).doOnNext { local.insertFilmDetails(it) })

    }

    override fun getAllFilms(): Observable<List<Film>> = remote.getAllFilms()
}