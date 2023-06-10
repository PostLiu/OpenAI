package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.model.SmsCodeType
import kotlinx.coroutines.flow.Flow

interface IGetSmsCodeUseCase {

    operator fun invoke(mobile: String, type: SmsCodeType): Flow<UIState<Any>>
}