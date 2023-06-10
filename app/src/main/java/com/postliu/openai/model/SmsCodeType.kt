package com.postliu.openai.model

sealed class SmsCodeType(val type: Int) {
    object Login : SmsCodeType(2)
    object Register : SmsCodeType(1)
    object Forget : SmsCodeType(3)
}