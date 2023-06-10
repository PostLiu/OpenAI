package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import kotlinx.coroutines.flow.Flow

interface IRegisterUseCase {

    operator fun invoke(
        mobile: String,
        smsCode: String,
        password: String,
        inviteCode: String,
        client: String
    ): Flow<UIState<Any>>
}