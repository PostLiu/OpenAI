package com.postliu.openai.domain.usecase

import androidx.paging.PagingData
import com.postliu.openai.domain.repository.IHomeRepository
import com.postliu.openai.model.local.LocalHomeGoodsData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPartnerAreaGoodsUseCase @Inject constructor(
    private val repository: IHomeRepository
) : IGetPartnerAreaGoodsUseCase {

    override fun invoke(goodsType: Int): Flow<PagingData<LocalHomeGoodsData>> {
        return repository.getPartnerAreaGoods(goodsType)
    }
}