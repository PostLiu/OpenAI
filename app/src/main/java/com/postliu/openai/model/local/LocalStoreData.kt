package com.postliu.openai.model.local

import androidx.annotation.Keep

@Keep
data class LocalStoreData(
    // 店铺id
    val storeId: Int,
    // 店铺名称
    val storeName: String,
    // 店铺logo
    val storeLogo: String,
    // 店铺详细地址
    val storeAddress: String,
    // 经度
    val storeLongitude: String,
    // 纬度
    val storeLatitude: String,
    val distance: String,
) {
    companion object {
        val default = LocalStoreData(0, "测试店铺", "", "广东省广州市白云区江夏村", "", "", "100m")
    }
}
