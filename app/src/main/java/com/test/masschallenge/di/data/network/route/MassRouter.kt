package com.test.masschallenge.di.data.network.route

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MassRouter {

    @GET("mobile-api/candidate/ads/{ad_id}")
    fun getAds(@Path("ad_id") ad_id: Int): Single<Response<String>>

}
