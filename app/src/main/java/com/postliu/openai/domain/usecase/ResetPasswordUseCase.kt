package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val repository: ILoginRepository
) : IResetPasswordUseCase {

    override fun invoke(mobile: String, smsCode: String, password: String): Flow<UIState<Any>> {
        return repository.reset(mobile, smsCode, password)
    }
}