package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.model.local.LocalLoginData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: ILoginRepository
) : ILoginUseCase {

    override fun invoke(mobile: String, password: String): Flow<UIState<LocalLoginData>> {
        return repository.login(mobile, password)
    }
}