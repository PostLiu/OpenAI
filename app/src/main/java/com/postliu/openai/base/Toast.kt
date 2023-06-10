package com.postliu.openai.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

inline fun <reified C : Context> C.showToast(msg: Any) = HandlerToast.showToast(this, msg)

object HandlerToast {

    private val handler = Handler(Looper.getMainLooper())

    private var toast: Toast? = null

    fun showToast(context: Context, msg: Any) {
        handler.post {
            if (toast != null) {
                toast?.cancel()
                toast = null
            }
            toast = Toast.makeText(context, "$msg", Toast.LENGTH_SHORT)
            toast?.show()
        }
    }
}