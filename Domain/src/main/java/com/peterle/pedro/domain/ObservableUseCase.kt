package com.peterle.pedro.domain

import com.peterle.pedro.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(params: Params? = null,
                     onNext: (T) -> Unit,
                     onError: (e: Throwable) -> Unit,
                     onComplte: (() -> Unit)? = null) {
        val single = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(object : DisposableObserver<T>(){
            override fun onComplete() {
                onComplte?.invoke()
            }

            override fun onNext(t: T) {
                onNext.invoke(t)
            }

            override fun onError(e: Throwable) {
                onError.invoke(e)
            }

        }))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.clear()
    }

}