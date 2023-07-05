package com.example.lifesemantics.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.repository.HospitalInfoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hospitalInfoRepository: HospitalInfoRepositoryImpl
) : ViewModel() {

    suspend fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double): Flow<PagingData<Item>> {
        val newHospitalInfo = hospitalInfoRepository.getHospitalInfo(hospitalName, 1, latitude, longitude)
            .cachedIn(viewModelScope)
        return newHospitalInfo
    }
}
