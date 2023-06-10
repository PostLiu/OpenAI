package com.postliu.openai.model.local

import androidx.annotation.Keep

@Keep
data class LocalHome(
    val bannerList: List<LocalBannerData> = emptyList(),
    val sortList: List<LocalSortData> = emptyList(),
    val news: List<LocalHomeGoodsData> = emptyList(),
    val areas: List<LocalHomeAreaData> = emptyList()
)
