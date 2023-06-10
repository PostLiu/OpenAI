package com.postliu.openai.domain.usecase

import androidx.paging.PagingData
import com.postliu.openai.model.local.LocalStoreData
import kotlinx.coroutines.flow.Flow

interface IGetStoreListUseCase {

    operator fun invoke(
        cityName: String?,
        areaId: String?,
        latitude: String?,
        longitude: String?,
        order: String?,
        sortId: Int?
    ): Flow<PagingData<LocalStoreData>>
}