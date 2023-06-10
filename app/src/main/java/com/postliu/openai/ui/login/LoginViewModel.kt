package com.postliu.openai.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postliu.openai.base.UIState
import com.postliu.openai.domain.usecase.ILoginUseCase
import com.postliu.openai.model.local.LocalLoginData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase
) : ViewModel() {

    private val mLogin = MutableSharedFlow<UIState<LocalLoginData>?>()
    val login = mLogin.asSharedFlow()

    fun dispatch(action: LoginAction) = when (action) {
        is LoginAction.Login -> {
            login(mobile = action.mobile, password = action.password)
        }
    }

    private fun login(mobile: String, password: String) = viewModelScope.launch {
        loginUseCase(mobile, password).onStart {
            mLogin.emit(UIState.Loading)
        }.catch {
            mLogin.emit(UIState.Throw(it))
        }.collectLatest {
            mLogin.emit(it)
        }
    }
}

sealed interface LoginAction {

    data class Login(val mobile: String, val password: String) : LoginAction
}
