package com.peterle.pedro.domain.interactors

import com.peterle.pedro.domain.ObservableUseCase
import com.peterle.pedro.domain.executor.PostExecutionThread
import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.domain.model.FilmDetails
import com.peterle.pedro.domain.repository.SWAPIRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetFilmDetails @Inject constructor(
        private val repository: SWAPIRepository,
        postExecutionThread: PostExecutionThread
): ObservableUseCase<FilmDetails, GetFilmDetails.Params>(postExecutionThread){

    override fun buildUseCaseObservable(params: Params?): Observable<FilmDetails> {
        if (params == null) throw IllegalArgumentException("Params can not be null")

        return repository.getFilmDetails(params.id)
    }

    class Params(val id: String)
}