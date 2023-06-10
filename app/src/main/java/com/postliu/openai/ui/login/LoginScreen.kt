package com.postliu.openai.ui.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.postliu.openai.R
import com.postliu.openai.base.SystemBarTransparent
import com.postliu.openai.base.UIState
import com.postliu.openai.ui.destinations.ForgetScreenDestination
import com.postliu.openai.ui.destinations.HomeScreenDestination
import com.postliu.openai.ui.destinations.RegisterScreenDestination
import com.postliu.openai.ui.theme.OpenAITheme
import com.postliu.openai.widgets.ShowLoading
import com.postliu.openai.widgets.ShowMessage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LoginScreen(navigator: DestinationsNavigator) {
    val viewModel: LoginViewModel = hiltViewModel()
    val loginState by viewModel.login.collectAsState(null)
    BackHandler {
        navigator.popBackStack(HomeScreenDestination, false)
    }
    LoginPage(login = { mobile, password ->
        viewModel.dispatch(LoginAction.Login(mobile, password))
    }, register = {
        navigator.navigate(RegisterScreenDestination)
    }, forget = {
        navigator.navigate(ForgetScreenDestination)
    }, back = {
        navigator.popBackStack(HomeScreenDestination, false)
    })
    loginState?.let {
        println("触发:${loginState}")
        ShowLoading(isShow = loginState is UIState.Loading)
        when (val state = it) {
            is UIState.Failed -> {
                ShowMessage(isShow = true, message = state.message)
            }

            is UIState.Loading -> {
            }

            is UIState.Success -> {
                navigator.popBackStack()
            }

            is UIState.Throw -> {
                ShowMessage(isShow = true, message = state.throwable.message ?: "未知错误")
            }
        }
    }
}

@Composable
fun LoginPage(
    back: () -> Unit = {},
    register: () -> Unit = {},
    forget: () -> Unit = {},
    login: (mobile: String, password: String) -> Unit
) {
    SystemBarTransparent(false)
    var mobile by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = R.drawable.login_background,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.FillWidth
        )
        TopAppBar(
            title = { Text(text = "登录") },
            navigationIcon = {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Rounded.Home, contentDescription = null)
                }
            },
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth(),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White
        )
        Card(
            modifier = Modifier
                .systemBarsPadding()
                .padding(horizontal = 14.dp, vertical = 120.dp)
                .fillMaxWidth(), elevation = 2.dp, shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "登录后更精彩", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                OutlinedTextField(value = mobile, onValueChange = {
                    mobile = it
                }, modifier = Modifier.fillMaxWidth(), leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }, label = {
                    Text(text = "请输入您的手机号")
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    },
                    label = {
                        Text(text = "请输入登录密码")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "立即注册",
                        fontSize = 12.sp,
                        modifier = Modifier.clickable { register() })
                    Text(text = "忘记密码",
                        fontSize = 12.sp,
                        modifier = Modifier.clickable { forget() })
                }
                Button(
                    onClick = {
                        login(mobile, password)
                    },
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    shape = CircleShape,
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(text = "登录")
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_3, widthDp = 375, heightDp = 812)
@Composable
fun LoginScreenPreview() {
    OpenAITheme {
        Box(modifier = Modifier.background(Color.White)) {
            LoginPage { mobile, password -> }
        }
    }
}