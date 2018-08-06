package com.peterle.pedro.starwars.di.module

import com.peterle.pedro.data.SWAPILocal
import com.peterle.pedro.local.SWAPILocalImpl
import dagger.Binds
import dagger.Module

@Module
abstract class LocalModule {

    @Binds
    abstract fun providesSWAPILocal(swapiLocalImpl: SWAPILocalImpl): SWAPILocal
}