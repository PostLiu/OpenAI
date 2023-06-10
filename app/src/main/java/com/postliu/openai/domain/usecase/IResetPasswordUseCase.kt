package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import kotlinx.coroutines.flow.Flow

interface IResetPasswordUseCase {

    operator fun invoke(mobile: String, smsCode: String, password: String): Flow<UIState<Any>>
}