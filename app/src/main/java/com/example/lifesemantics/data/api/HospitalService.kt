package com.example.lifesemantics.data.api

import com.example.lifesemantics.data.entity.HospitalInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HospitalService {

    @GET("/B551182/hospInfoServicev2/getHospBasisList")
    suspend fun getHospitalInfo(
        @Query("ServiceKey") serviceKey: String
    ) : HospitalInfoResponse
}