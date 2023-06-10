package com.postliu.openai.model.datasource

import com.postliu.openai.api.ApiService
import com.postliu.openai.model.local.LocalStoreData
import java.math.BigDecimal
import java.math.RoundingMode

class StoreListDataSource(
    private val cityName: String?,
    private val latitude: String?,
    private val longitude: String?,
    private val areaId: String?,
    private val order: String?,
    private val sortId: Int?,
    private val apiService: ApiService
) : BasePagingSource<LocalStoreData>() {

    override suspend fun loadPaging(loadParams: LoadParams<Int>): LoadResult<Int, LocalStoreData> {
        return runCatching {
            val page = loadParams.key ?: 1
            val params = buildMap<String, Any> {
                cityName?.let { put("cityName", it) }
                areaId?.let { put("area_id", it) }
                order?.let { put("order", it) }
                sortId?.let { put("storeclass_id", it) }
                longitude?.let { put("lon", it) }
                latitude?.let { put("lat", it) }
                put("page", page)
                put("list_rows", loadParams.loadSize)
            }
            val result = apiService.storeList(params).result.list
            val newData = result?.map {
                LocalStoreData(
                    storeId = it.storeId,
                    storeName = it.storeName.orEmpty(),
                    storeLogo = it.storeLogo.orEmpty(),
                    storeAddress = it.storeAddress.orEmpty(),
                    storeLongitude = it.storeLongitude.orEmpty(),
                    storeLatitude = it.storeLatitude.orEmpty(),
                    distance = it.distance.orEmpty().ifEmpty { "0" }.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
                        .run {
                            println("默认:$this")
                            val zero = BigDecimal(0).setScale(2)
                            val default = BigDecimal(1000).setScale(2)
                            val km = this.div(default)
                            when (this) {
                                zero..default -> {
                                    buildString { append(zero).append("M") }
                                }

                                else -> {
                                    buildString { append(km).append("KM") }
                                }
                            }
                        }
                )
            }.orEmpty()
            LoadResult.Page(
                data = newData,
                prevKey = null,
                nextKey = if (newData.size < loadParams.loadSize) null else page + 1
            )
        }.getOrElse { LoadResult.Error(it) }
    }
}