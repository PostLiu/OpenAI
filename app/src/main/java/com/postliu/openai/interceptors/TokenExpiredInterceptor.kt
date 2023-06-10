package com.postliu.openai.interceptors

import com.drake.channel.sendEvent
import com.postliu.openai.base.Constants
import com.postliu.openai.base.DataResult
import com.postliu.openai.mmkv.MMKVConstant
import com.postliu.openai.utils.GsonUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.GzipSource
import java.io.IOException
import java.nio.charset.StandardCharsets

object TokenExpiredInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runCatching {
            val originRequest = chain.request()
            val originResponse = chain.proceed(originRequest)
            if (originResponse.isSuccessful) {
                originResponse.body?.let { responseBody ->
                    val contentLength = responseBody.contentLength()
                    val source = responseBody.source()
                    source.request(Long.MAX_VALUE)
                    var buffer = source.buffer
                    if ("gzip".equals(originResponse.headers["Content-Encoding"], true)) {
                        GzipSource(buffer.clone()).use { gzippedResponseBody ->
                            buffer = Buffer()
                            buffer.writeAll(gzippedResponseBody)
                        }
                    }
                    val contentType = responseBody.contentType()
                    val charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
                    if (contentLength != 0L) {
                        val string = buffer.clone().readString(charset)
                        val result = with(GsonUtils) {
                            fromJson(string, DataResult::class.java)
                        }
                        if (result.isTokenExpired) {
                            MMKVConstant.token = null
                            MMKVConstant.isLogin = false
                            sendEvent(true, Constants.LOGIN_AGAIN)
                            return fakeResponse(chain, result.msg)
                        }
                        return originResponse
                    }
                }
            } else {
                throw IOException(originResponse.message)
            }
            originResponse
        }.getOrElse { fakeResponse(chain, it.message.orEmpty()) }
    }

    private fun fakeResponse(chain: Interceptor.Chain, message: String): Response {
        return Response.Builder()
            .code(200)
            .protocol(chain.proceed(chain.request()).protocol)
            .message(message)
            .body(
                GsonUtils.toJson(DataResult(chain.proceed(chain.request()).code, message, null)).toResponseBody()
            )
            .request(chain.request())
            .build()
    }
}