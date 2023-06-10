package com.postliu.openai.model.local

import androidx.annotation.Keep

@Keep
data class LocalHomeGoodsData(
    val id: Int,
    val url: String,
    val name: String,
    val price: String,
    val point: String,
) {
    companion object {
        val default = LocalHomeGoodsData(0, "", "测试商品", "0.01", "0")
    }
}
