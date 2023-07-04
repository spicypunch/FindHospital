package com.example.lifesemantics.repository

import com.example.lifesemantics.BuildConfig
import com.example.lifesemantics.data.entity.HospitalInfoResponse
import com.example.lifesemantics.data.api.HospitalService
import javax.inject.Inject

class HospitalInfoRepositoryImpl @Inject constructor(
    private val hospitalService: HospitalService
) : HospitalInfoRepository {

    override suspend fun getHospitalInfo(hospitalName: String, pageNo: Int, latitude: Double, longitude: Double): HospitalInfoResponse {
        return hospitalService.getHospitalInfo(
            serviceKey = BuildConfig.API_KEY,
            hospitalName = hospitalName,
            pageNo = pageNo.toString(),
            latitude = latitude.toString(),
            longitude = longitude.toString(),
            radius = "5000"
        )
    }
}