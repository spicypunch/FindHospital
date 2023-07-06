package com.example.lifesemantics.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.repository.HospitalInfoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hospitalInfoRepository: HospitalInfoRepositoryImpl
) : ViewModel() {

    private var _data = MutableLiveData<PagingData<Item>>()

    suspend fun getHospitalInfo(
        hospitalName: String,
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<Item>> {
        return hospitalInfoRepository.getHospitalInfo(hospitalName, latitude, longitude)
            .cachedIn(viewModelScope)
    }

    fun getHospitalInfo2(
        hospitalName: String,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            val result = hospitalInfoRepository.getHospitalInfo(hospitalName, latitude, longitude)
                .cachedIn(viewModelScope)
                .asLiveData()
            _data.value = result.value
        }
    }
}