package com.example.lifesemantics.util

import com.example.lifesemantics.data.entity.Item
import androidx.paging.PagingSource
import com.example.lifesemantics.data.api.HospitalService
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val service: HospitalService
): PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: 1
            val perPage = params.loadSize

            val response = service.getHospitalInfo(

            )
        } catch (e: Exception) {

        }
    }
}