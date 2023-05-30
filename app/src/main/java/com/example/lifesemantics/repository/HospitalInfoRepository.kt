package com.example.lifesemantics.repository

import com.example.lifesemantics.data.entity.HospitalInfoResponse

interface HospitalInfoRepository {

    suspend fun getHospitalInfo(): HospitalInfoResponse
}