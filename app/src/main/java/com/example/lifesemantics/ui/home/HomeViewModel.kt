package com.example.lifesemantics.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifesemantics.data.entity.HospitalInfoResponse
import com.example.lifesemantics.repository.HospitalInfoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hospitalInfoRepository: HospitalInfoRepositoryImpl
) : ViewModel() {
    // 현재 페이지를 가지고 있는 LiveData
    private var _cnt = MutableLiveData(1)
    val cnt: LiveData<Int>
        get() = _cnt

    private var _hospitalInfo = MutableLiveData<HospitalInfoResponse>()
    val hospitalInfo: LiveData<HospitalInfoResponse>
        get() = _hospitalInfo

    private var hospitalName = ""
    private var latitude = 0.0
    private var longitude = 0.0
    private var pageNo = 1

    fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                _hospitalInfo.value = hospitalInfoRepository.getHospitalInfo(
                    hospitalName = hospitalName,
                    pageNo = pageNo,
                    latitude = latitude,
                    longitude = longitude
                )
                this@HomeViewModel.hospitalName = hospitalName
                this@HomeViewModel.latitude = latitude
                this@HomeViewModel.longitude = longitude

                _cnt.value = pageNo
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }

    fun nextInfo() {
        viewModelScope.launch {
            try {
                _hospitalInfo.value = hospitalInfoRepository.getHospitalInfo(
                    hospitalName = hospitalName,
                    pageNo = ++pageNo,
                    latitude = latitude,
                    longitude = longitude
                )
                _cnt.value = pageNo
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }

    fun previousInfo() {
        viewModelScope.launch {
            try {
                _hospitalInfo.value = hospitalInfoRepository.getHospitalInfo(
                    hospitalName = hospitalName,
                    pageNo = --pageNo,
                    latitude = latitude,
                    longitude = longitude
                )
                _cnt.value = pageNo
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }
}