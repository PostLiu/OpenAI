package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.model.local.LocalHome
import kotlinx.coroutines.flow.Flow

interface IGetHomeConfigUseCase {

    operator fun invoke(): Flow<UIState<LocalHome>>
}