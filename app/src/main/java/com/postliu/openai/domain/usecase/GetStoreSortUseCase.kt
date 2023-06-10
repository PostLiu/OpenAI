package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.domain.repository.IStoreRepository
import com.postliu.openai.model.local.LocalSortData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreSortUseCase @Inject constructor(
    private val repository: IStoreRepository
) : IGetStoreSortUseCase {

    override fun invoke(longitude: String?, latitude: String?): Flow<UIState<List<LocalSortData>>> {
        return repository.storeSortList(longitude, latitude)
    }
}