package com.example.lifesemantics.ui.main

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
class MainViewModel @Inject constructor(
    private val hospitalInfoRepository: HospitalInfoRepositoryImpl
): ViewModel() {

    private var _hospitalInfo = MutableLiveData<HospitalInfoResponse>()
    val hospitalInfo: LiveData<HospitalInfoResponse>
        get() = _hospitalInfo

    fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                _hospitalInfo.value = hospitalInfoRepository.getHospitalInfo(hospitalName, latitude, longitude)
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }
}