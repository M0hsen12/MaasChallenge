package com.test.masschallenge.di.modules

import com.test.masschallenge.ui.activities.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityInjectorsModule {


    @ContributesAndroidInjector(modules = [ FragmentBuildersModule::class])

    abstract fun mainActivityInjector(): MapsActivity


}