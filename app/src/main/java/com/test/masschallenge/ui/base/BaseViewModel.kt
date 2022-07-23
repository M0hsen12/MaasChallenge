package com.test.masschallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.masschallenge.di.data.appManger.DataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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
