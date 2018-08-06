package com.peterle.pedro.starwars.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peterle.pedro.domain.interactors.GetAllFilmsData
import com.peterle.pedro.domain.interactors.GetFilmDetails
import com.peterle.pedro.presentation.DetailsViewModel
import com.peterle.pedro.presentation.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
        private val getAllFilmsData: GetAllFilmsData,
        private val getFilmDetails: GetFilmDetails
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {

            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(getAllFilmsData)
                isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(getFilmDetails)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }