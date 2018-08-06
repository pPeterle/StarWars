package com.peterle.pedro.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peterle.pedro.domain.interactors.GetFilmDetails
import com.peterle.pedro.presentation.mapper.FilmDetailsViewMapper
import com.peterle.pedro.presentation.model.FilmDetailsView

class DetailsViewModel(private val getFilmDetails: GetFilmDetails) : ViewModel() {

    private val viewState = MutableLiveData<ViewState<FilmDetailsView>>()

    fun getViewState(): LiveData<ViewState<FilmDetailsView>> = viewState

    fun fetchData(id: String) {
        if (viewState.value != null) return

        viewState.postValue(ViewState(ViewState.Status.LOADING))
        getFilmDetails.execute(GetFilmDetails.Params(id),
                onNext = {
                    viewState.postValue(ViewState(ViewState.Status.SUCCESS, FilmDetailsViewMapper.maptToView(it)))
                },
                onError = {
                    viewState.postValue(ViewState(ViewState.Status.ERROR, error = it))
                })
    }

    override fun onCleared() {
        super.onCleared()
        getFilmDetails.dispose()
    }
}