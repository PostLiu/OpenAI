package com.postliu.openai.domain.usecase

import androidx.paging.PagingData
import com.postliu.openai.model.local.LocalHomeGoodsData
import kotlinx.coroutines.flow.Flow

interface IGetPartnerAreaGoodsUseCase {

    operator fun invoke(goodsType: Int): Flow<PagingData<LocalHomeGoodsData>>
}