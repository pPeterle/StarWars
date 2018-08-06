package com.peterle.pedro.starwars.di

import android.app.Application
import com.peterle.pedro.starwars.App
import com.peterle.pedro.starwars.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    RemoteModule::class,
    DataModule::class,
    LocalModule::class,
    PresentationModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: App)
}