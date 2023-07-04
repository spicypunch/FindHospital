package com.example.lifesemantics.repository

import com.example.lifesemantics.data.entity.HospitalInfoResponse
import io.reactivex.Single

interface HospitalInfoRepository {

    fun getHospitalInfo(hospitalName: String, pageNo: Int, latitude: Double, longitude: Double): Single<HospitalInfoResponse>
}