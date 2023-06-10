package com.postliu.openai.model.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkStoreSortParentData(
    @SerializedName("classification")
    val list: List<NetworkStoreSortData>?,
)

@Keep
data class NetworkStoreSortData(
    @SerializedName("pic")
    val pic: String?,
    @SerializedName("storeclass_bail")
    val storeclassBail: Int?,
    @SerializedName("storeclass_id")
    val storeclassId: Int?,
    @SerializedName("storeclass_name")
    val storeclassName: String?,
    @SerializedName("storeclass_sort")
    val storeclassSort: Int?
)