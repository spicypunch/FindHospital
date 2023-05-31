package com.example.lifesemantics.repository

import com.example.lifesemantics.data.entity.HospitalInfoResponse
import com.example.lifesemantics.data.api.HospitalService
import javax.inject.Inject

class HospitalInfoRepositoryImpl @Inject constructor(
    private val hospitalService: HospitalService
) : HospitalInfoRepository {

    override suspend fun getHospitalInfo(hospitalName: String, latitude: Double, longitude: Double): HospitalInfoResponse {
        return hospitalService.getHospitalInfo(
            "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
            hospitalName,
            latitude.toString(),
            longitude.toString(),
            "5000"
        )
    }
}