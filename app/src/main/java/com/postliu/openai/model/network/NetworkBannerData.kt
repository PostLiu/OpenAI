package com.postliu.openai.model.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkBannerData(
    @SerializedName("adv_code")
    val advCode: String?,
    @SerializedName("adv_enabled")
    val advEnabled: Int?,
    @SerializedName("adv_enddate")
    val advEnddate: Int?,
    @SerializedName("adv_id")
    val advId: Int?,
    @SerializedName("adv_sort")
    val advSort: Int?,
    @SerializedName("adv_startdate")
    val advStartdate: Int?,
    @SerializedName("adv_title")
    val advTitle: String?,
    @SerializedName("adv_type")
    val advType: String?,
    @SerializedName("adv_typedate")
    val advTypedate: String?,
    @SerializedName("ap_id")
    val apId: Int?
)

@Keep
data class NetworkTop3Data(
    @SerializedName("goods_id")
    val goodsId: Int,
    @SerializedName("goods_name")
    val goodsName: String,
    @SerializedName("goods_price")
    val goodsPrice: String,
    @SerializedName("goods_image")
    val goodsImage: String
)

@Keep
data class NetworkHomeConfigData(
    @SerializedName("banners")
    val banners: List<NetworkBannerData>?,
    @SerializedName("top3")
    val top: List<NetworkTop3Data>?,
)