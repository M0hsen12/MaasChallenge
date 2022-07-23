package com.test.masschallenge.di.data.appManger

import android.content.Context
import com.test.masschallenge.di.data.network.NetworkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager
@Inject
constructor(
    override val context: Context,
    override val networkManager: NetworkManager
    ) : DataManager


