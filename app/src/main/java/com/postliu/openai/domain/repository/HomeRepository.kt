package com.postliu.openai.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.postliu.openai.api.ApiService
import com.postliu.openai.base.UIState
import com.postliu.openai.model.datasource.HomeGoodsDataSource
import com.postliu.openai.model.datasource.PartnerAreaGoodsDataSource
import com.postliu.openai.model.local.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService
) : IHomeRepository {

    private fun bannerAndNewGoodsFlow() = flow {
        val result = apiService.getHomeConfig(emptyMap()).result
        emit(result)
    }

    private fun homeSortFlow() = flow {
        val result = apiService.getHomePagerSort().result
        emit(result)
    }

    override fun getHomeConfig(): Flow<UIState<LocalHome>> {
        return combine(bannerAndNewGoodsFlow(), homeSortFlow()) { config, sort ->
            val banners = config.banners?.map {
                LocalBannerData(id = it.advId ?: 0, url = it.advCode.orEmpty())
            }.orEmpty()
            val newGoods = config.top?.map {
                LocalHomeGoodsData(
                    id = it.goodsId,
                    url = it.goodsImage,
                    price = it.goodsPrice,
                    point = "",
                    name = it.goodsName
                )
            }.orEmpty()
            val sortList = sort.sortList?.map {
                LocalSortData(id = it.id ?: 0, name = it.value.orEmpty(), url = it.image.orEmpty())
            }.orEmpty()
            UIState.Success(
                LocalHome(
                    bannerList = banners,
                    news = newGoods,
                    sortList = sortList,
                    areas = LocalHomeAreaData.default
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getGoods(): Flow<PagingData<LocalHomeGoodsData>> {
        return Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 1, initialLoadSize = 10),
            pagingSourceFactory = { HomeGoodsDataSource(apiService) }).flow.flowOn(Dispatchers.IO)
    }

    override fun getPartnerAreaGoods(goodsType: Int): Flow<PagingData<LocalHomeGoodsData>> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 1),
            pagingSourceFactory = {
                PartnerAreaGoodsDataSource(goodsType, apiService)
            }
        ).flow.flowOn(Dispatchers.IO)
    }
}
