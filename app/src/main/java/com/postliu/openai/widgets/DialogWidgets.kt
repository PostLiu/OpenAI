package com.postliu.openai.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

typealias OnDismissRequest = () -> Unit

@Composable
fun ShowLoading(isShow: Boolean) {
    var showState by remember(key1 = isShow) {
        mutableStateOf(isShow)
    }
    if (showState) {
        Dialog(onDismissRequest = {
            showState = false
        }, properties = DialogProperties()) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0x50000000), RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp), color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
fun ShowMessage(
    isShow: Boolean,
    message: CharSequence,
    onDismissRequest: OnDismissRequest = {},
    buttonClick: () -> Unit = {}
) {
    var showState by remember(isShow) {
        mutableStateOf(isShow)
    }
    if (showState) {
        Dialog(
            onDismissRequest = {
                showState = false
                onDismissRequest()
            }, properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.background, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "温馨提示", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    text = message.toString(), modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
                Button(onClick = {
                    showState = false
                    buttonClick()
                }, modifier = Modifier.fillMaxWidth(), shape = CircleShape) {
                    Text(text = "确认")
                }
            }
        }
    }
}