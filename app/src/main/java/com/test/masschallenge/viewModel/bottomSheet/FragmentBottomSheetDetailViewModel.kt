package com.test.masschallenge.viewModel.bottomSheet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.masschallenge.di.data.appManger.DataManager
import com.test.masschallenge.model.response.detail.Details
import com.test.masschallenge.model.response.places.Places
import com.test.masschallenge.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FragmentBottomSheetDetailViewModel @Inject constructor(
    dataManager: DataManager,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(dataManager, compositeDisposable) {

    val detailLiveData = MutableLiveData<Details>()

    fun getLocationDetail(id: String) {
        disposable[0]?.dispose()
        disposable[0] =
            mDataManager.networkManager.getMassApi()
                .getPlaceDetail(id)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribe({
                    if (it.isSuccessful) {
                        detailLiveData.postValue(it.body())
                    }
                }, {
                    errorLiveData.postValue(it)
                })

        addDisposable(disposable[0])
    }

}
