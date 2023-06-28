package com.example.lifesemantics.repository

import com.example.lifesemantics.data.entity.HospitalInfoResponse

interface HospitalInfoRepository {

    fun getHospitalInfo(
        hospitalName: String,
        latitude: Double,
        longitude: Double
    ): HospitalInfoResponse
}
