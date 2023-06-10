package com.postliu.openai.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.postliu.openai.Icons
import com.postliu.openai.R
import com.postliu.openai.icons.MainExchangeIconNormal
import com.postliu.openai.icons.MainExchangeIconSelected
import com.postliu.openai.icons.MainHomeIconNormal
import com.postliu.openai.icons.MainHomeIconSelected
import com.postliu.openai.icons.MainPartnerIconNormal
import com.postliu.openai.icons.MainPartnerIconSelected
import com.postliu.openai.icons.MainStoreIconNormal
import com.postliu.openai.icons.MainStoreIconSelected
import com.postliu.openai.icons.MainUserIconNormal
import com.postliu.openai.icons.MainUserIconSelected
import com.postliu.openai.ui.destinations.*
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
     val normalIcon: ImageVector,
     val selectedIcon: ImageVector,
    val label: String,
) {
    Home(
        direction = HomeScreenDestination,
        normalIcon = Icons.MainHomeIconNormal,
        selectedIcon = Icons.MainHomeIconSelected,
        label = "首页"
    ),

    Partner(
        direction = PartnerScreenDestination,
        normalIcon = Icons.MainPartnerIconNormal,
        selectedIcon = Icons.MainPartnerIconSelected,
        label = "合伙人区",
    ),
    Exchange(
        direction = ExchangeScreenDestination,
        normalIcon = Icons.MainExchangeIconNormal,
        selectedIcon = Icons.MainExchangeIconSelected,
        label = "兑换商城"
    ),
    LocalLife(
        direction = StoreScreenDestination,
        normalIcon = Icons.MainStoreIconNormal,
        selectedIcon = Icons.MainStoreIconSelected,
        label = "本地生活"
    ),
    User(
        direction = UserScreenDestination,
        normalIcon = Icons.MainUserIconNormal,
        selectedIcon = Icons.MainUserIconSelected,
        label = "我的"
    )
}