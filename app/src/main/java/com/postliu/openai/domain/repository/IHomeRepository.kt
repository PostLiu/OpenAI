package com.postliu.openai.domain.repository

import androidx.paging.PagingData
import com.postliu.openai.base.UIState
import com.postliu.openai.model.local.LocalHome
import com.postliu.openai.model.local.LocalHomeGoodsData
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    fun getHomeConfig(): Flow<UIState<LocalHome>>

    fun getGoods(): Flow<PagingData<LocalHomeGoodsData>>

    fun getPartnerAreaGoods(goodsType: Int): Flow<PagingData<LocalHomeGoodsData>>
}