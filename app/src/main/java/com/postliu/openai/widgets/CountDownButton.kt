package com.postliu.openai.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.postliu.openai.base.ComposeCountDownTimer
import com.postliu.openai.base.rememberCountDownTimer

@Composable
fun CountDownButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textStyle: TextStyle = TextStyle(),
    isStart: Boolean = false,
    isEnable: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    countDownTimer: ComposeCountDownTimer = rememberCountDownTimer(millisInFuture = 60000, countDownInterval = 1000)
) {

    var start by remember(isStart) {
        mutableStateOf(isStart)
    }
    var countDownTimerText by remember {
        mutableStateOf("验证码")
    }
    var enabled by remember(isEnable) {
        mutableStateOf(isEnable)
    }
    if (start) {
        enabled = false
        countDownTimer.cancelAndStart()
    }
    DisposableEffect(key1 = countDownTimer, effect = {
        countDownTimer.setOnTick {
            countDownTimerText = buildString {
                append(it / 1000)
                append("s")
            }
        }.setOnFinish {
            start = false
            enabled = true
            countDownTimerText = "重新发送"
            countDownTimer.cancel()
        }
        onDispose {
            start = false
            enabled = true
            countDownTimer.cancel()
        }
    })
    Button(
        onClick = {
            onClick()
        },
        enabled = enabled,
        modifier = modifier,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Text(text = countDownTimerText, style = textStyle)
    }
}