package com.postliu.openai.base

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 处理透明状态栏,透明导航栏时的图标反色
 *
 * @param statusBarDarkIcons
 * @param navigationBarDarkIcons
 */
@Composable
fun SystemBarTransparent(statusBarDarkIcons: Boolean = true, navigationBarDarkIcons: Boolean = true) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = statusBarDarkIcons)
        systemUiController.setNavigationBarColor(color = Color.Transparent, darkIcons = navigationBarDarkIcons)
    }
}

/**
 * 设置状态栏颜色,导航栏颜色,以及图标反色
 *
 * @param statusBarColor
 * @param navigationBarColor
 * @param statusBarDarkIcons
 * @param navigationBarDarkIcons
 */
@Composable
fun SystemBarColor(
    statusBarColor: Color = MaterialTheme.colors.primary,
    navigationBarColor: Color = MaterialTheme.colors.primary,
    statusBarDarkIcons: Boolean = statusBarColor.luminance() > 0.5f,
    navigationBarDarkIcons: Boolean = navigationBarColor.luminance() > 0.5f
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, statusBarDarkIcons)
        systemUiController.setNavigationBarColor(navigationBarColor, navigationBarDarkIcons)
    }
}