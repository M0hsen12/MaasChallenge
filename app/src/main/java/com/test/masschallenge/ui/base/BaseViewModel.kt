package com.test.masschallenge.ui.base

import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.masschallenge.di.data.appManger.DataManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.Result
import java.util.*


open class BaseViewModel(
    val mDataManager: DataManager,
    private val mCompositeDisposable: CompositeDisposable
) : ViewModel() {

    var disposable: HashMap<Int, Disposable?> = HashMap()
    var errorLiveData = MutableLiveData<Throwable>()



    fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            this.mCompositeDisposable.add(it)
        }
    }



    override fun onCleared() {
        this.mCompositeDisposable.dispose()
        this.mCompositeDisposable.clear()
        super.onCleared()
    }





}
