package com.postliu.openai.model.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkStorePageData(
    @SerializedName("store_list")
    val list: List<NetworkStoreData>?,
)

@Keep
data class NetworkStoreData(
    @SerializedName("area_info")
    val areaInfo: Any?,
    @SerializedName("classification_name")
    val classificationName: String?,
    @SerializedName("distance")
    val distance: String?,
    @SerializedName("goods_count")
    val goodsCount: Int?,
    @SerializedName("store_address")
    val storeAddress: String?,
    @SerializedName("store_banner")
    val storeBanner: String?,
    @SerializedName("store_id")
    val storeId: Int,
    @SerializedName("store_latitude")
    val storeLatitude: String?,
    @SerializedName("store_logo")
    val storeLogo: String?,
    @SerializedName("store_longitude")
    val storeLongitude: String?,
    @SerializedName("store_name")
    val storeName: String?,
    @SerializedName("storeclass_id")
    val storeclassId: Int
)