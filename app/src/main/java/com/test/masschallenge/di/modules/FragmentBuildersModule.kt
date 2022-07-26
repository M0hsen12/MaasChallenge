package com.test.masschallenge.di.modules

import com.test.masschallenge.ui.bottomSheet.FragmentBottomSheetDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector()
    abstract fun fragmentBottomSheetDetailInjector(): FragmentBottomSheetDetail

}