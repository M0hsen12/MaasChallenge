package com.test.masschallenge.di.modules

import com.test.masschallenge.di.data.appManger.AppDataManager
import com.test.masschallenge.di.data.appManger.DataManager
import com.test.masschallenge.di.data.network.NetworkModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module(
    includes = [
        NetworkModule::class
    ]
)
open class AppModule {


    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }


}