package com.example.lifesemantics.repository

import androidx.paging.PagingData
import com.example.lifesemantics.data.entity.Item
import kotlinx.coroutines.flow.Flow

interface HospitalInfoRepository {

    suspend fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double): Flow<PagingData<Item>>
}