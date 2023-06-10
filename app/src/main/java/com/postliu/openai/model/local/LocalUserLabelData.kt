package com.postliu.openai.model.local

import androidx.annotation.Keep
import com.postliu.openai.R

@Keep
data class LocalUserLabelData(
    val icon: Int,
    val name: String,
    val nameColor: Long,
    val backgroundColor: Long
) {
    companion object {
        val default = listOf(
            LocalUserLabelData(
                icon = R.drawable.user_label_1,
                name = "市合伙人",
                nameColor = 0xFF43915E,
                backgroundColor = 0xFFEAFFE1
            ), LocalUserLabelData(
                name = "区合伙人",
                icon = R.drawable.user_label_2,
                nameColor = 0xFF926B12,
                backgroundColor = 0xFFFFE9B6
            ), LocalUserLabelData(
                name = "商家合伙人",
                icon = R.drawable.user_label_3,
                nameColor = 0xFF5F6C98,
                backgroundColor = 0xFFE1E8FF
            ), LocalUserLabelData(
                name = "企业合伙人",
                icon = R.drawable.user_label_4,
                nameColor = 0xFF7852AA,
                backgroundColor = 0xFFEDE1FF
            ), LocalUserLabelData(
                name = "联合创始人",
                icon = R.drawable.user_label_5,
                nameColor = 0xFFAA5252,
                backgroundColor = 0xFFFFE1E1
            )
        )
    }
}
