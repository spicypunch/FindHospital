package com.example.lifesemantics.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lifesemantics.BuildConfig
import com.example.lifesemantics.data.api.HospitalService
import com.example.lifesemantics.data.entity.Item
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val service: HospitalService,
    private val hospitalName: String,
    private val latitude: Double,
    private val longitude: Double
) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 1
        return try {
            val response = service.getHospitalInfo(
                serviceKey = BuildConfig.API_KEY,
                hospitalName = hospitalName,
                pageNo = page.toString(),
                latitude = latitude.toString(),
                longitude = longitude.toString(),
                radius = "5000"
            )

            val items = response.body?.items?.itemList
            LoadResult.Page(
                data = items!!,
                prevKey = if (page == 0) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }
}