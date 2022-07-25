package com.test.masschallenge.di.data.network.intercepter

import android.content.Context
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor constructor(
    val context: Context
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .build()
        )
    }
}