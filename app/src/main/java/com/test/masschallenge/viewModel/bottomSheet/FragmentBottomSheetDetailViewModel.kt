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
                    Log.e("TAG", "getPlacesDetail: ${it.isSuccessful} ${it.errorBody()?.string()}")
                    if (it.isSuccessful) {
                        Log.e("TAG", "getPlaces: ${it.body()?.name?.en}")
                        detailLiveData.postValue(it.body())
                    }
                }, {
                    Log.e("TAG", "getPlaces: $it")
                })

        addDisposable(disposable[0])
    }

}
