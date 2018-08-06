package com.peterle.pedro.domain.interactors

import com.peterle.pedro.domain.ObservableUseCase
import com.peterle.pedro.domain.executor.PostExecutionThread
import com.peterle.pedro.domain.model.Film
import com.peterle.pedro.domain.repository.SWAPIRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllFilmsData @Inject constructor(
        private val repository: SWAPIRepository,
        postExecutionThread: PostExecutionThread
): ObservableUseCase<List<Film>, Unit>(postExecutionThread){

    override fun buildUseCaseObservable(params: Unit?): Observable<List<Film>> = repository.getAllFilms()

}