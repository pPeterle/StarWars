package com.peterle.pedro.remote.service

import com.peterle.pedro.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SWAPIService {

    @GET("films")
    fun getAllFilms(): Single<ApiResult>

    @GET("films/{id}")
    fun getFilmDetails(@Path("id") id: String): Observable<FilmModel>

    @GET("people/{id}")
    fun getPeopleById( @Path("id") id: String): Observable<PeopleModel>

    @GET("planets/{id}")
    fun getPlanetById( @Path("id") id: String): Observable<PlanetModel>
}