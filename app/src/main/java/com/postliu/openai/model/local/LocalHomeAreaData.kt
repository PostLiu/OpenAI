package com.postliu.openai.model.local

import com.postliu.openai.R

data class LocalHomeAreaData(
    val id: Int,
    val name: String,
    val subtitle: String,
    val background: Int,
    val area: HomeArea
) {
    companion object {
        internal val default = listOf(
            LocalHomeAreaData(
                id = 0,
                name = "合伙人专区",
                subtitle = "专区好礼",
                background = R.drawable.home_partner_area_background,
                area = HomeArea.Partner
            ),
            LocalHomeAreaData(
                id = 1,
                name = "兑换专区",
                subtitle = "积分兑换好礼",
                background = R.drawable.home_exchange_area_background,
                area = HomeArea.Exchange
            )
        )
    }
}

sealed interface HomeArea {
    object Partner : HomeArea
    object Exchange : HomeArea
}
