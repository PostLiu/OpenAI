package com.postliu.openai.domain.usecase

import androidx.paging.PagingData
import com.postliu.openai.domain.repository.IStoreRepository
import com.postliu.openai.model.local.LocalStoreData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreListUseCase @Inject constructor(
    private val repository: IStoreRepository
) : IGetStoreListUseCase {

    override fun invoke(
        cityName: String?, areaId: String?, latitude: String?, longitude: String?, order: String?, sortId: Int?
    ): Flow<PagingData<LocalStoreData>> {
        return repository.storeList(cityName, areaId, latitude, longitude, order, sortId)
    }
}