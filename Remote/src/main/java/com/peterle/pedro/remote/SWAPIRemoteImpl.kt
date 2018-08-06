package com.peterle.pedro.remote

import com.peterle.pedro.data.SWAPIRemote
import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.domain.model.FilmDetails
import com.peterle.pedro.domain.model.People
import com.peterle.pedro.domain.model.Planet
import com.peterle.pedro.remote.mapper.FilmModelMapper
import com.peterle.pedro.remote.mapper.PeopleModelMapper
import com.peterle.pedro.remote.mapper.PlanetModelMapper
import com.peterle.pedro.remote.service.SWAPIService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SWAPIRemoteImpl @Inject constructor(private val service: SWAPIService) : SWAPIRemote {
    override fun getAllFilms(): Observable<List<Film>> =
            service.getAllFilms()
                    .flatMapObservable { apiResult -> Observable.just(apiResult.results) }
                    .map { it.map { FilmModelMapper.mapFromModel(it) } }

    override fun getFilmDetails(id: String): Observable<FilmDetails> {
        //I have to do this, because the endpoint is diferent of the episodeId
        val newId = when (id) {
            "4" -> "1"
            "5" -> "2"
            "6" -> "3"
            "1" -> "4"
            "2" -> "5"
            "3" -> "6"
            else -> id
        }
        return service.getFilmDetails(newId)
                .flatMap { film ->
                    Observable.zip(
                            Observable
                                    .fromIterable(film.characters)
                                    .flatMap { peopleUrl ->
                                        service.getPeopleById(getLastPath(peopleUrl))
                                    }
                                    .map { PeopleModelMapper.mapFromModel(it) }
                                    .toList()
                                    .toObservable(),
                            Observable.fromIterable(film.planets)
                                    .flatMap { peopleUrl ->
                                        service.getPlanetById(getLastPath(peopleUrl))
                                    }
                                    .map { PlanetModelMapper.mapFromModel(it) }
                                    .toList()
                                    .toObservable(),

                            BiFunction<List<People>, List<Planet>, FilmDetails> { people, planets ->
                                FilmDetails(film.title, film.episodeId, film.releaseDate, people, planets)
                            }
                    )
                }
    }

    private fun getLastPath(url: String): String = url.replaceFirst(".*/([^/?]+).*".toRegex(), "$1")

}