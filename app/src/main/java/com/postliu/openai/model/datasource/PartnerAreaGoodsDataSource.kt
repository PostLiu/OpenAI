package com.postliu.openai.model.datasource

import com.postliu.openai.api.ApiService
import com.postliu.openai.model.local.LocalHomeGoodsData
import kotlinx.coroutines.delay

class PartnerAreaGoodsDataSource(
    private val goodsType: Int,
    private val apiService: ApiService
) : BasePagingSource<LocalHomeGoodsData>() {

    override suspend fun loadPaging(loadParams: LoadParams<Int>): LoadResult<Int, LocalHomeGoodsData> {
        return runCatching {
            val page = loadParams.key ?: 1
            val params = buildMap<String, Any> {
                put("goods_type", goodsType)
                put("page", page)
                put("per_page", loadParams.loadSize)
            }
            delay(1000)
            val result = apiService.getHomeGoods(params).successData?.list.orEmpty()
            val newData = result.map {
                LocalHomeGoodsData(
                    id = it.goodsId ?: -1,
                    url = it.goodsImageUrl.orEmpty(),
                    name = it.goodsName.orEmpty(),
                    price = it.goodsPrice.orEmpty(),
                    point = it.orderPoints.orEmpty()
                )
            }
            LoadResult.Page(
                data = newData,
                prevKey = null,
                nextKey = if (newData.size < loadParams.loadSize) null else page + 1
            )
        }.getOrElse { LoadResult.Error(it) }
    }
}