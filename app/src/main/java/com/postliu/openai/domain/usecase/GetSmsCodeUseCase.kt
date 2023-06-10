package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.model.SmsCodeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetSmsCodeUseCase @Inject constructor(
    private val repository: ILoginRepository
) : IGetSmsCodeUseCase {

    override fun invoke(mobile: String, type: SmsCodeType): Flow<UIState<Any>> {
        return repository.sendSmsCode(mobile, type).onStart {
            emit(UIState.Loading)
        }.catch { emit(UIState.Throw(it)) }
    }
}