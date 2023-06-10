package com.postliu.openai.model.local

import androidx.annotation.Keep

@Keep
data class LocalSortData(
    val id: Int,
    val name: String,
    val url: String,
) {
    constructor() : this(-1, "", "")
}
