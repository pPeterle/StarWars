package com.peterle.pedro.starwars.di

import com.peterle.pedro.starwars.ui.activity.DetailsActivity
import com.peterle.pedro.starwars.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainAcitivty(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindDetailsActivity(): DetailsActivity

}