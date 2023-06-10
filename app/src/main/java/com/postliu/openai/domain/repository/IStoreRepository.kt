package com.postliu.openai.domain.repository

import androidx.paging.PagingData
import com.postliu.openai.base.UIState
import com.postliu.openai.model.local.LocalSortData
import com.postliu.openai.model.local.LocalStoreData
import kotlinx.coroutines.flow.Flow

interface IStoreRepository {

    fun storeSortList(longitude: String?, latitude: String?): Flow<UIState<List<LocalSortData>>>

    fun storeList(
        cityName: String?,
        areaId: String?,
        latitude: String?,
        longitude: String?,
        order: String?,
        sortId: Int?
    ): Flow<PagingData<LocalStoreData>>
}