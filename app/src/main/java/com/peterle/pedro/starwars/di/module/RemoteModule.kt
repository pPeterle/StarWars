package com.peterle.pedro.starwars.di.module

import com.peterle.pedro.data.SWAPIRemote
import com.peterle.pedro.domain.executor.PostExecutionThread
import com.peterle.pedro.remote.SWAPIRemoteImpl
import com.peterle.pedro.remote.service.SWAPIService
import com.peterle.pedro.remote.service.SWAPIServiceFactory
import com.peterle.pedro.starwars.BuildConfig
import com.peterle.pedro.presentation.executor.UiThread
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesSWAPIService(): SWAPIService = SWAPIServiceFactory.makeSWAPIService(BuildConfig.DEBUG)
    }

    @Binds
    abstract fun bindsSWAPIRemoteImpl(swapiRemoteImpl: SWAPIRemoteImpl): SWAPIRemote

}