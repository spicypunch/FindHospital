package com.example.lifesemantics.data.api

import com.example.lifesemantics.data.entity.HospitalInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HospitalService {

    @GET("/B551182/hospInfoServicev2/getHospBasisList")
    suspend fun getHospitalInfo(
        @Query("ServiceKey") serviceKey: String,
        @Query("yadmNm") hospitalName: String,
        @Query("yPos") latitude: String,
        @Query("xPos") longitude: String,
        @Query("radius") radius: String
    ) : HospitalInfoResponse
}