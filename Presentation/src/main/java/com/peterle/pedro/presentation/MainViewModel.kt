package com.peterle.pedro.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.peterle.pedro.domain.interactors.GetAllFilmsData
import com.peterle.pedro.presentation.mapper.FilmViewMapper
import com.peterle.pedro.presentation.model.FilmView

class MainViewModel(private val getAllFilmsData: GetAllFilmsData) : ViewModel() {

    private val viewState = MutableLiveData<ViewState<List<FilmView>>>()

    init {
        fetchData()
    }

    fun getViewState(): LiveData<ViewState<List<FilmView>>> = viewState

    private fun fetchData() {
        if (viewState.value != null) return

        viewState.value = ViewState(ViewState.Status.LOADING)
        getAllFilmsData.execute(null,
                onNext = {
                    viewState.postValue(ViewState(ViewState.Status.SUCCESS,
                            it.map { FilmViewMapper.maptToView(it) }.sortedBy { it.episodeId }))
                },
                onError = {
                    viewState.postValue(ViewState(ViewState.Status.ERROR, error = it))
                })
    }

    override fun onCleared() {
        super.onCleared()
        getAllFilmsData.dispose()
    }
}