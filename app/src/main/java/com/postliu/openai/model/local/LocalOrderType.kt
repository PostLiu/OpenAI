package com.postliu.openai.model.local

import com.postliu.openai.R

sealed class LocalOrderType(val type: Int, val name: String, val icon: Int, orderStatus: String) {
    object All : LocalOrderType(type = 0, name = "全部", icon = 0, orderStatus = "")
    object TobePaid :
        LocalOrderType(type = 1, name = "待支付", icon = R.drawable.user_to_be_paid, orderStatus = "state_new")

    object TobeShipped :
        LocalOrderType(type = 2, name = "待发货", icon = R.drawable.user_to_be_shipped, orderStatus = "state_pay")

    object TobeReceived :
        LocalOrderType(type = 3, name = "待收货", icon = R.drawable.user_to_be_received, orderStatus = "state_send")

    object Completed :
        LocalOrderType(type = 4, name = "已完成", icon = R.drawable.user_completed, orderStatus = "state_noeval")

    companion object {
        val default1 = listOf(TobePaid, TobeShipped, TobeReceived, Completed)
    }
}