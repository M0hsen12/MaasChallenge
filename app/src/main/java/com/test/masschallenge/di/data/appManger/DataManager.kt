package com.test.masschallenge.di.data.appManger

import android.content.Context
import com.test.masschallenge.di.data.network.NetworkManager


interface DataManager {

    val context: Context

    val networkManager: NetworkManager

    // we can add every thing we want in here like dataBase manager , download manager , account manger and ...

}
