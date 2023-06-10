package com.postliu.openai.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.postliu.openai.base.SystemBarColor
import com.postliu.openai.base.UIState
import com.postliu.openai.base.UIState.Companion.doCatch
import com.postliu.openai.base.UIState.Companion.doFailed
import com.postliu.openai.base.UIState.Companion.doStart
import com.postliu.openai.base.UIState.Companion.doSuccess
import com.postliu.openai.base.showToast
import com.postliu.openai.ui.theme.OpenAITheme
import com.postliu.openai.widgets.CountDownButton
import com.postliu.openai.widgets.ShowLoading
import com.postliu.openai.widgets.ShowMessage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RegisterScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    var isSend by remember { mutableStateOf(false) }
    val viewModel: RegisterScreenViewModel = hiltViewModel()
    var message by remember {
        mutableStateOf("")
    }
    val smsCodeState by viewModel.smsCode.collectAsStateWithLifecycle(null)
    val registerState by viewModel.register.collectAsStateWithLifecycle(null)

    LaunchedEffect(key1 = smsCodeState, block = {
        smsCodeState?.let { uiState ->
            isSend = uiState is UIState.Success
            uiState.doStart {}.doCatch {
                message = it.message ?: "未知错误"
            }.doFailed {
                message = it
            }.doSuccess {
                context.showToast("请注意查收短信验证码")
            }
        }
    })

    LaunchedEffect(key1 = registerState, block = {
        registerState?.let {
            it.doStart { }.doCatch { throwable ->
                message = throwable.message ?: "未知错误"
            }.doFailed { msg ->
                message = msg
            }.doSuccess {
                context.showToast("注册成功")
                navigator.popBackStack()
            }
        }
    })

    RegisterPage(isStartSendSms = isSend, back = { navigator.popBackStack() }, sendSmsCode = { mobile ->
        viewModel.dispatch(RegisterAction.SendSmsCode(mobile))
    }, register = { mobile, smsCode, password, passwordAgain, inviteCode ->
        viewModel.dispatch(RegisterAction.RegisterAccount(mobile, smsCode, password, passwordAgain, inviteCode))
    })

    ShowLoading(isShow = smsCodeState is UIState.Loading || registerState is UIState.Loading)

    ShowMessage(
        isShow = smsCodeState is UIState.Failed || smsCodeState is UIState.Throw || registerState is UIState.Failed || registerState is UIState.Throw,
        message = message
    )

}

@Composable
fun RegisterPage(
    isStartSendSms: Boolean = false,
    back: () -> Unit = {},
    sendSmsCode: (mobile: String) -> Unit = {},
    register: (mobile: String, smsCode: String, password: String, passwordAgain: String, inviteCode: String) -> Unit = { _, _, _, _, _ -> }
) {
    SystemBarColor()
    val scrollState = rememberScrollState()
    var mobile by rememberSaveable { mutableStateOf("") }
    var smsCode by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordAgain by rememberSaveable { mutableStateOf("") }
    var inviteCode by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        TopAppBar(title = {
            Text(text = "注册账号")
        }, navigationIcon = {
            IconButton(onClick = {
                back()
            }) {
                Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = null)
            }
        })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            OutlinedTextField(
                value = mobile,
                onValueChange = { mobile = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
                label = {
                    Text(text = "请输入登录手机号码")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { KeyboardActions.Default.onNext })
            )
            OutlinedTextField(
                value = smsCode,
                onValueChange = { smsCode = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
                label = {
                    Text(text = "短信验证码")
                },
                trailingIcon = {
                    Box(modifier = Modifier.padding(end = 12.dp)) {
                        CountDownButton(
                            onClick = { sendSmsCode(mobile) },
                            isStart = isStartSendSms,
                            isEnable = !isStartSendSms,
                            elevation = ButtonDefaults.elevation(0.dp),
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { KeyboardActions.Default.onNext })
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
                label = {
                    Text(text = "请设置登录密码")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { KeyboardActions.Default.onNext })
            )
            OutlinedTextField(
                value = passwordAgain,
                onValueChange = { passwordAgain = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
                label = {
                    Text(text = "请再次输入登录密码")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { KeyboardActions.Default.onNext })
            )
            OutlinedTextField(
                value = inviteCode,
                onValueChange = { inviteCode = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
                label = {
                    Text(text = "推荐人手机号码")
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { KeyboardActions.Default.onNext })
            )
            Button(
                onClick = {
                    register(mobile, smsCode, password, passwordAgain, inviteCode)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                shape = CircleShape
            ) {
                Text(text = "注册")
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    OpenAITheme {
        RegisterPage()
    }
}