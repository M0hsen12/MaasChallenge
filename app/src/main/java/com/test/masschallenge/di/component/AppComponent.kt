package com.test.masschallenge.di.component

import android.content.Context
import com.test.masschallenge.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
//        ,
//        ActivityInjectorsModule::class,
//        ViewModelFactoryModule::class,
//        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)



}

