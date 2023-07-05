package com.example.lifesemantics.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lifesemantics.data.api.HospitalService
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.util.PagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HospitalInfoRepositoryImpl @Inject constructor(
    private val hospitalService: HospitalService
) : HospitalInfoRepository {

    override suspend fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double): Flow<PagingData<Item>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            PagingSource(hospitalService, hospitalName, latitude, longitude)
        }.flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}