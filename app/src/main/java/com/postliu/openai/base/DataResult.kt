package com.postliu.openai.base

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.postliu.openai.exceptions.DataResultException

@Keep
data class DataResult<out T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message", alternate = ["msg"])
    val msg: String,
    @SerializedName("result", alternate = ["data"])
    val `data`: T
) {

    val isSuccess get() = code == SUCCESS

    val isTokenExpired get() = code == TOKEN_EXPIRED || code == SELLER_TOKEN_EXPIRED

    val failedMsg get() = if (code != SUCCESS) msg else null

    val failedData get() = if (code != SUCCESS) data else null

    val successMsg get() = if (code == SUCCESS) msg else null

    val successData get() = if (code == SUCCESS) data else null

    val result get() = if (isSuccess) data else throw DataResultException(code, msg)

    companion object {
        const val SUCCESS = 10000
        const val TOKEN_EXPIRED = 11001
        const val SELLER_TOKEN_EXPIRED = 13001

        inline fun <T> DataResult<T>.failed(block: (msg: String) -> Unit) = apply {
            failedMsg?.let(block)
        }

        inline fun <T> DataResult<T>.success(block: (data: T) -> Unit) = apply {
            successData?.let(block)
        }
    }


}
