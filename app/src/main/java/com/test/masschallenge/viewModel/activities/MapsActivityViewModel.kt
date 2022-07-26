package com.test.masschallenge.viewModel.activities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.masschallenge.di.data.appManger.DataManager
import com.test.masschallenge.model.response.places.Places
import com.test.masschallenge.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MapsActivityViewModel @Inject constructor(
    dataManager: DataManager,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(dataManager, compositeDisposable) {

    val placesLiveData = MutableLiveData<Places>()

    init {
        getPlaces(60.188, 24.950)
    }

    fun getPlaces(lat: Double, lng: Double) {
        disposable[DISPOSABLE_LOCATIONS]?.dispose()
        disposable[DISPOSABLE_LOCATIONS] =
            mDataManager.networkManager.getMassApi()
                .getPlaces(null, null, "$lat,$lng,10", null, "20", "0")
                .delay(2000,TimeUnit.MILLISECONDS).subscribe({
                    Log.e("TAG", "getPlaces: ${it.isSuccessful}")
                    if (it.isSuccessful) {
                        Log.e("TAG", "getPlaces: ${it.body()?.data?.size}")
                        placesLiveData.postValue(it.body())
                    }
                }, {
                    Log.e("TAG", "getPlaces: $it")
                })

        addDisposable(disposable[DISPOSABLE_LOCATIONS])

    }

    companion object {
        const val DISPOSABLE_LOCATIONS = 0
    }

}