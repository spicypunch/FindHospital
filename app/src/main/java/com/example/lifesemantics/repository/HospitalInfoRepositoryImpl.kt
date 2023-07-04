package com.example.lifesemantics.repository

import com.example.lifesemantics.BuildConfig
import com.example.lifesemantics.data.api.HospitalService
import com.example.lifesemantics.data.entity.HospitalInfoResponse
import io.reactivex.Single
import javax.inject.Inject

class HospitalInfoRepositoryImpl @Inject constructor(
    private val hospitalService: HospitalService
) : HospitalInfoRepository {

    override fun getHospitalInfo(hospitalName: String, pageNo: Int, latitude: Double, longitude: Double): Single<HospitalInfoResponse> {
        return Single.fromCallable {
            hospitalService.getHospitalInfo(
                serviceKey = BuildConfig.API_KEY,
                hospitalName = hospitalName,
                pageNo = pageNo.toString(),
                latitude = latitude.toString(),
                longitude = longitude.toString(),
                radius = "5000"
            )
        }
    }

}