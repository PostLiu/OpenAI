package com.postliu.openai.model.local

import androidx.annotation.Keep

/**
 * 轮播图数据
 *
 * @property id
 * @property url
 * @constructor Create empty Local banner data
 */
@Keep
data class LocalBannerData(
    val id: Int,
    val url: String
)
