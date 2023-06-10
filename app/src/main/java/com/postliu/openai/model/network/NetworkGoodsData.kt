package com.postliu.openai.model.network

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

@Keep
data class NetworkGoodsData(
    @SerializedName("discount")
    val discount: String?,
    @SerializedName("evaluation_count")
    val evaluationCount: Int?,
    @SerializedName("evaluation_good_star")
    val evaluationGoodStar: Int?,
    @SerializedName("goods_advword")
    val goodsAdvword: String?,
    @SerializedName("goods_id")
    val goodsId: Int?,
    @SerializedName("goods_image")
    val goodsImage: String?,
    @SerializedName("goods_image_url")
    val goodsImageUrl: String?,
    @SerializedName("goods_marketprice")
    val goodsMarketprice: String?,
    @SerializedName("goods_name")
    val goodsName: String?,
    @SerializedName("goods_price")
    val goodsPrice: String?,
    @SerializedName("goods_promotion_price")
    val goodsPromotionPrice: String?,
    @SerializedName("goods_promotion_type")
    val goodsPromotionType: Int?,
    @SerializedName("goods_salenum")
    val goodsSalenum: Int?,
    @SerializedName("group_flag")
    val groupFlag: Boolean?,
    @SerializedName("is_goodsfcode")
    val isGoodsfcode: Int?,
    @SerializedName("is_have_gift")
    val isHaveGift: Int?,
    @SerializedName("is_platform_store")
    val isPlatformStore: Int?,
    @SerializedName("is_presell")
    val isPresell: Int?,
    @SerializedName("is_virtual")
    val isVirtual: Int?,
    @SerializedName("order_points")
    val orderPoints: String?,
    @SerializedName("shares")
    val shares: String?,
    @SerializedName("store_id")
    val storeId: Int?,
    @SerializedName("store_name")
    val storeName: String?,
    @SerializedName("xianshi_flag")
    val xianshiFlag: Boolean?
)

@Keep
data class NetworkPageGoodsData(
    @SerializedName("goods_list")
    val list: List<NetworkGoodsData>?,
    @SerializedName("page_total")
    val pageTotal: Int?,
)