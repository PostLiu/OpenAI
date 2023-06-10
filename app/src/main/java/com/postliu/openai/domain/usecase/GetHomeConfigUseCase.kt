package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.domain.repository.IHomeRepository
import com.postliu.openai.model.local.LocalHome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeConfigUseCase @Inject constructor(
    private val repository: IHomeRepository
) : IGetHomeConfigUseCase {

    override fun invoke(): Flow<UIState<LocalHome>> {
        return repository.getHomeConfig()
    }
}