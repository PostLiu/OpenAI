package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.model.local.LocalLoginData
import kotlinx.coroutines.flow.Flow

interface ILoginUseCase {

    operator fun invoke(mobile:String,password:String): Flow<UIState<LocalLoginData>>
}