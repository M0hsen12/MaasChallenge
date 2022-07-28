package com.test.masschallenge.viewModel.activities

import android.util.Log
import android.view.textclassifier.TextLanguage
import androidx.lifecycle.MutableLiveData
import com.test.masschallenge.di.data.appManger.DataManager
import com.test.masschallenge.model.response.activities.Activities
import com.test.masschallenge.model.response.events.Events
import com.test.masschallenge.model.response.places.Places
import com.test.masschallenge.ui.base.BaseViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MapsActivityViewModel @Inject constructor(
    dataManager: DataManager,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(dataManager, compositeDisposable) {

    val locationsLiveData = MutableLiveData<Triple<Places, Activities, Events>>()


    fun getPlaces(
        lat: Double,
        lng: Double,
        distance: Int = DISTANCE,
        tagSearch: String? = null,
        tagFilter: String? = null,
        language: String? = null,
        limit: String = LIMIT,
        start: String = START
    ) {
        disposable[DISPOSABLE_LOCATIONS]?.dispose()
        disposable[DISPOSABLE_LOCATIONS] =
            Single.zip(
                mDataManager.networkManager.getMassApi()
                    .getPlaces(tagSearch, tagFilter, "$lat,$lng,$distance", language, limit, start),

                mDataManager.networkManager.getMassApi()
                    .getActivities(tagSearch, tagFilter, "$lat,$lng,$distance", language, limit, start),

                mDataManager.networkManager.getMassApi()
                    .getEvents(tagSearch, tagFilter, "$lat,$lng,$distance", language, limit, start)
            ) { places, activities, events ->
                Triple(places, activities, events)
            }.subscribe({
                locationsLiveData.postValue(it)
            }, {
                Log.e("TAG", "error: $it")
            })

        addDisposable(disposable[DISPOSABLE_LOCATIONS])

    }

    companion object {
        const val DISPOSABLE_LOCATIONS = 0
        const val DISTANCE = 10
        const val LIMIT = "10"
        const val START = "0"
    }

}