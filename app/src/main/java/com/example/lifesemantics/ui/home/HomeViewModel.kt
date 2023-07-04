package com.example.lifesemantics.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifesemantics.data.entity.HospitalInfoResponse
import com.example.lifesemantics.repository.HospitalInfoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hospitalInfoRepositoryimpl: HospitalInfoRepositoryImpl
) : ViewModel() {
    private var _hospitalInfo = MutableLiveData<HospitalInfoResponse>()
    val hospitalInfo: LiveData<HospitalInfoResponse>
        get() = _hospitalInfo

    private val compositeDisposable = CompositeDisposable()

    private var pageNo = 1

    fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double) {
        pageNo = 1
        val disposable = hospitalInfoRepositoryimpl.getHospitalInfo(
            hospitalName = hospitalName,
            pageNo = pageNo,
            latitude = latitude,
            longitude = longitude
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _hospitalInfo.value = response
            }, { error ->
                Log.e("Error", error.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}