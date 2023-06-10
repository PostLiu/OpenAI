package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: ILoginRepository
) : IRegisterUseCase {

    override fun invoke(
        mobile: String,
        smsCode: String,
        password: String,
        inviteCode: String,
        client: String
    ): Flow<UIState<Any>> {
        return repository.register(mobile, smsCode, password, inviteCode, client).onStart {
            emit(UIState.Loading)
        }.catch { emit(UIState.Throw(it)) }
    }
}