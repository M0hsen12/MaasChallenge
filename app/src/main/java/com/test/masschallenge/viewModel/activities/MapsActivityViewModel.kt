package com.test.masschallenge.viewModel.activities

import android.util.Log
import com.test.masschallenge.di.data.appManger.DataManager
import com.test.masschallenge.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapsActivityViewModel @Inject constructor(
    dataManager: DataManager,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(dataManager, compositeDisposable) {

init {
    Log.e("TAG", "view model is rdy: ", )
}


}