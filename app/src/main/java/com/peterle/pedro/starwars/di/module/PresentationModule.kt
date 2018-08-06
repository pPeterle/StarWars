package com.peterle.pedro.starwars.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peterle.pedro.domain.executor.PostExecutionThread
import com.peterle.pedro.presentation.DetailsViewModel
import com.peterle.pedro.presentation.MainViewModel
import com.peterle.pedro.presentation.executor.UiThread
import com.peterle.pedro.starwars.di.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsPostExecutionThread(uiThread: UiThread): PostExecutionThread
}