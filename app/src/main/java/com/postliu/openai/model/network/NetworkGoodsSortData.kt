package com.postliu.openai.model.network


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkGoodsSortData(
    @SerializedName("class_list")
    val sortList: List<Sort>?
) {
    @Keep
    data class Sort(
        @SerializedName("children")
        val children: List<Children>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("value")
        val value: String?
    ) {
        @Keep
        data class Children(
            @SerializedName("children")
            val children: List<Children>?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("value")
            val value: String?
        ) {
            @Keep
            data class Children(
                @SerializedName("children")
                val children: List<Children>?,
                @SerializedName("id")
                val id: Int?,
                @SerializedName("image")
                val image: String?,
                @SerializedName("value")
                val value: String?
            ) {
                @Keep
                data class Children(
                    @SerializedName("id")
                    val id: Int?,
                    @SerializedName("value")
                    val value: String?,
                    @SerializedName("image")
                    val image: String?,
                )
            }
        }
    }
}