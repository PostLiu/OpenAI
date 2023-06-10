package com.postliu.openai.ui.forget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postliu.openai.base.UIState
import com.postliu.openai.domain.usecase.IGetSmsCodeUseCase
import com.postliu.openai.domain.usecase.IResetPasswordUseCase
import com.postliu.openai.model.SmsCodeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetScreenViewModel @Inject constructor(
    private val getSmsCodeUseCase: IGetSmsCodeUseCase,
    private val resetPasswordUseCase: IResetPasswordUseCase
) : ViewModel() {
    private val mSmsCode = MutableSharedFlow<UIState<Any>>()
    val smsCode = mSmsCode.asSharedFlow()

    private val mReset = MutableSharedFlow<UIState<Any>?>()
    val reset = mReset.asSharedFlow()

    fun dispatch(action: ForgetAction) {
        when (action) {
            is ForgetAction.SendSmsCode -> {
                sendSmsCode(mobile = action.mobile, type = SmsCodeType.Forget)
            }

            is ForgetAction.Reset -> {
                with(action) {
                    reset(mobile, smsCode, password)
                }
            }
        }
    }

    private fun sendSmsCode(mobile: String, type: SmsCodeType) = viewModelScope.launch {
        getSmsCodeUseCase(mobile, type).collectLatest {
            mSmsCode.emit(it)
        }
    }

    private fun reset(mobile: String, smsCode: String, password: String) =
        viewModelScope.launch {
            resetPasswordUseCase(mobile, smsCode, password).collectLatest { mReset.emit(it) }
        }
}

sealed interface ForgetAction {
    data class SendSmsCode(val mobile: String) : ForgetAction
    data class Reset(
        val mobile: String,
        val smsCode: String,
        val password: String,
        val passwordAgain: String
    ) : ForgetAction
}
