package com.postliu.openai.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postliu.openai.base.UIState
import com.postliu.openai.domain.usecase.IGetSmsCodeUseCase
import com.postliu.openai.domain.usecase.IRegisterUseCase
import com.postliu.openai.model.SmsCodeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val getSmsCodeUseCase: IGetSmsCodeUseCase,
    private val registerUseCase: IRegisterUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "RegisterScreenViewModel"
    }

    private val mSmsCode = MutableSharedFlow<UIState<Any>?>()
    val smsCode = mSmsCode.asSharedFlow()

    private val mRegister = MutableSharedFlow<UIState<Any>?>()
    val register = mRegister.asSharedFlow()

    fun dispatch(action: RegisterAction) {
        when (action) {

            is RegisterAction.SendSmsCode -> {
                sendSmsCode(action.mobile)
            }

            is RegisterAction.RegisterAccount -> {
                with(action) {
                    register(mobile, smsCode, password, inviteCode)
                }
            }
        }
    }

    private fun sendSmsCode(mobile: String) = viewModelScope.launch {
        getSmsCodeUseCase(mobile, SmsCodeType.Register).collectLatest {
            mSmsCode.emit(it)
        }
    }

    private fun register(mobile: String, smsCode: String, password: String, inviteCode: String) =
        viewModelScope.launch {
            registerUseCase(mobile, smsCode, password, inviteCode, "android").collectLatest {
                Log.i(TAG, "register: $it")
                mRegister.emit(it)
            }
        }
}

sealed interface RegisterAction {
    data class SendSmsCode(val mobile: String) : RegisterAction
    data class RegisterAccount(
        val mobile: String,
        val smsCode: String,
        val password: String,
        val passwordAgain: String,
        val inviteCode: String
    ) : RegisterAction
}
