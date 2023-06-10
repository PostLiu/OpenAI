package com.postliu.openai.interceptors

import com.postliu.openai.mmkv.MMKVConstant
import okhttp3.Interceptor
import okhttp3.Response

object AddTokenHeaderInterceptor : Interceptor {

    private val token get() = MMKVConstant.token

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().run {
            token?.let { addHeader("X-DS-KEY", it) }
            build()
        })
    }
}