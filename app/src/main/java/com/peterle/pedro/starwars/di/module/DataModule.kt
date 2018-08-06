package com.peterle.pedro.starwars.di.module

import com.peterle.pedro.data.SWAPIDataRepository
import com.peterle.pedro.domain.repository.SWAPIRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsSWAPIRepository(swapiDataRepository: SWAPIDataRepository): SWAPIRepository
}