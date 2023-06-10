package com.postliu.openai.base

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat

@Stable
interface WindowController {

    var decorFitsSystemWindows: Boolean
}

@Composable
fun rememberWindowController(window: Window? = findWindow()): WindowController {
    return remember(window) {
        AndroidWindowController(window)
    }
}

internal class AndroidWindowController(
    private val window: Window?
) : WindowController {

    private var isDecorFitsSystemWindows = true

    override var decorFitsSystemWindows: Boolean = isDecorFitsSystemWindows
        get() = isDecorFitsSystemWindows
        set(value) {
            field = value
            isDecorFitsSystemWindows = field
            if (window != null) {
                WindowCompat.setDecorFitsSystemWindows(window, isDecorFitsSystemWindows)
            }
        }
}

@Composable
private fun findWindow(): Window? =
    (LocalView.current.parent as? DialogWindowProvider)?.window
        ?: LocalView.current.context.findWindow()

private tailrec fun Context.findWindow(): Window? =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> null
    }