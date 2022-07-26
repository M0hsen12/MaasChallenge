package com.test.masschallenge.viewModel.bottomSheet

import android.util.Log
import com.test.masschallenge.di.data.appManger.DataManager
import com.test.masschallenge.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FragmentBottomSheetDetailViewModel @Inject constructor(
    dataManager: DataManager,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(dataManager, compositeDisposable) {

    init {
        Log.e("TAG", "sheet init viewmodel: ", )
    }

}
