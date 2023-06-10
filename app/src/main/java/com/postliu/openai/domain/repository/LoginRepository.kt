package com.postliu.openai.domain.repository

import com.postliu.openai.api.ApiService
import com.postliu.openai.base.DataResult.Companion.failed
import com.postliu.openai.base.DataResult.Companion.success
import com.postliu.openai.base.UIState
import com.postliu.openai.mmkv.MMKVConstant
import com.postliu.openai.model.SmsCodeType
import com.postliu.openai.model.local.LocalLoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) : ILoginRepository {

    override fun login(mobile: String, password: String): Flow<UIState<LocalLoginData>> {
        return flow {
            val params = hashMapOf<String, Any>().apply {
                put("username", mobile)
                put("password", password)
                put("client_type", "android")
            }
            val result = apiService.login(params)
            result.failed {
                emit(UIState.Failed(it))
            }.success {
                MMKVConstant.isLogin = true
                MMKVConstant.token = it.token
                emit(UIState.Success(LocalLoginData(it.token.orEmpty())))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun sendSmsCode(mobile: String, type: SmsCodeType): Flow<UIState<Any>> {
        return flow {
            val params = buildMap<String, Any> {
                put("phone", mobile)
                put("type", type.type)
            }
            val result = apiService.getSmsCode(params)
            result.failed {
                emit(UIState.Failed(it))
            }.success {
                emit(UIState.Success(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun register(
        mobile: String,
        smsCode: String,
        password: String,
        inviteCode: String,
        client: String
    ): Flow<UIState<Any>> {
        return flow {
            val params = buildMap<String, Any> {
                put("phone", mobile)
                put("captcha", smsCode)
                put("password", password)
                put("inviter_id", inviteCode)
                put("client", "android")
            }
            apiService.register(params).failed {
                emit(UIState.Failed(it))
            }.success {
                emit(UIState.Success(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun reset(
        mobile: String,
        smsCode: String,
        password: String
    ): Flow<UIState<Any>> {
        return flow {
            val params = buildMap<String, Any> {
                put("phone", mobile)
                put("captcha", smsCode)
                put("password", password)
                put("client", "android")
            }
            apiService.resetPassword(params).failed {
                emit(UIState.Failed(it))
            }.success {
                emit(UIState.Success(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}