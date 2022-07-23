package com.test.masschallenge.di.data.network

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.test.masschallenge.di.data.network.route.MassRouter
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Retrofit
import javax.inject.Inject


class NetworkManager @Inject constructor(
    var networkConnectivity: BehaviorSubject<Connectivity>,
    @NetworkModule.RestApi private val retrofitRestApi: Retrofit
) {

    fun <T> create(tClass: Class<T>): T {
        return retrofitRestApi.create(tClass)
    }

    fun getEbayApi(): MassRouter {
        return retrofitRestApi.create(MassRouter::class.java)
    }

}
