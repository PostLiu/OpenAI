package com.postliu.openai.domain.usecase

import androidx.paging.PagingData
import com.postliu.openai.domain.repository.IHomeRepository
import com.postliu.openai.model.local.LocalHomeGoodsData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeGoodsUseCase @Inject constructor(
    private val repository: IHomeRepository
) : IGetHomeGoodsUseCase {

    override fun invoke(): Flow<PagingData<LocalHomeGoodsData>> {
        return repository.getGoods()
    }
}