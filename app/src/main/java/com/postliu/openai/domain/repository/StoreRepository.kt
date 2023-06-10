package com.postliu.openai.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.postliu.openai.api.ApiService
import com.postliu.openai.base.DataResult.Companion.failed
import com.postliu.openai.base.DataResult.Companion.success
import com.postliu.openai.base.UIState
import com.postliu.openai.model.datasource.StoreListDataSource
import com.postliu.openai.model.local.LocalSortData
import com.postliu.openai.model.local.LocalStoreData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val apiService: ApiService
) : IStoreRepository {

    override fun storeSortList(longitude: String?, latitude: String?): Flow<UIState<List<LocalSortData>>> {
        return flow {
            val params = buildMap {
                longitude?.let { put("lon", it) }
                latitude?.let { put("lat", it) }
            }
            apiService.storeSortList(params).failed {
                emit(UIState.Failed(it))
            }.success { parentData ->
                val data = parentData.list?.map {
                    LocalSortData(id = it.storeclassId ?: 0, name = it.storeclassName.orEmpty(), url = it.pic.orEmpty())
                }.orEmpty()
                emit(UIState.Success(data))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun storeList(
        cityName: String?,
        areaId: String?,
        latitude: String?,
        longitude: String?,
        order: String?,
        sortId: Int?
    ): Flow<PagingData<LocalStoreData>> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 1),
            pagingSourceFactory = {
                StoreListDataSource(
                    cityName = cityName,
                    latitude = latitude,
                    longitude = longitude,
                    areaId = areaId,
                    order = order,
                    sortId = sortId,
                    apiService = apiService
                )
            }
        ).flow.flowOn(Dispatchers.IO)
    }
}