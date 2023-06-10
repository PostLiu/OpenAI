package com.postliu.openai.base

import android.os.CountDownTimer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberCountDownTimer(millisInFuture: Long, countDownInterval: Long): ComposeCountDownTimer = remember {
    return@remember ComposeCountDownTimer(millisInFuture, countDownInterval)
}

typealias OnTick = (millisInFuture: Long) -> Unit
typealias OnFinish = () -> Unit

class ComposeCountDownTimer(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {

    private var onTick: OnTick? = null

    private var onFinish: OnFinish? = null

    override fun onTick(millisUntilFinished: Long) {
        onTick?.invoke(millisUntilFinished)
    }

    override fun onFinish() {
        onFinish?.invoke()
    }

    fun cancelAndStart() {
        cancel()
        start()
    }

    fun setOnTick(onTick: OnTick) = apply {
        this.onTick = onTick
    }

    fun setOnFinish(onFinish: OnFinish) = apply {
        this.onFinish = onFinish
    }
}