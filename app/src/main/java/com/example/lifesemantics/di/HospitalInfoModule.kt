package com.example.lifesemantics.di

import com.example.lifesemantics.data.api.HospitalService
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HospitalInfoModule {
    private const val BASE_URL = "https://apis.data.go.kr"

    @Singleton
    @Provides
    fun provideHospitalInfoService(): HospitalService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                )
            ).build().create(HospitalService::class.java)
    }
}