package com.postliu.openai.domain.repository

import com.postliu.openai.base.UIState
import com.postliu.openai.model.SmsCodeType
import com.postliu.openai.model.local.LocalLoginData
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {

    fun login(mobile: String, password: String): Flow<UIState<LocalLoginData>>

    fun sendSmsCode(mobile: String, type: SmsCodeType): Flow<UIState<Any>>

    fun register(
        mobile: String,
        smsCode: String,
        password: String,
        inviteCode: String,
        client: String
    ): Flow<UIState<Any>>

    fun reset(
        mobile: String,
        smsCode: String,
        password: String,
    ): Flow<UIState<Any>>
}