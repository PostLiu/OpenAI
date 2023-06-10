package com.postliu.openai

import androidx.compose.ui.graphics.vector.ImageVector
import com.postliu.openai.icons.GoodsPointIcon
import com.postliu.openai.icons.IcLauncherBackground
import com.postliu.openai.icons.IcUserAvatarDefaultLogo
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
import com.postliu.openai.icons.SearchIcon
import com.postliu.openai.icons.StoreCartIcon
import com.postliu.openai.icons.StoreSearchIcon
import com.postliu.openai.icons.UserCompleted
import com.postliu.openai.icons.UserToBePaid
import com.postliu.openai.icons.UserToBeReceived
import com.postliu.openai.icons.UserToBeShipped
import kotlin.collections.List as ____KtList

public object Icons

private var __AllIcons: ____KtList<ImageVector>? = null

public val Icons.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(MainStoreIconNormal, UserToBeShipped, StoreSearchIcon,
        MainPartnerIconSelected, MainHomeIconNormal, SearchIcon, UserToBePaid,
        IcUserAvatarDefaultLogo, MainPartnerIconNormal, UserToBeReceived, MainStoreIconSelected,
        GoodsPointIcon, MainExchangeIconNormal, MainUserIconSelected, MainExchangeIconSelected,
        MainHomeIconSelected, MainUserIconNormal, UserCompleted, StoreCartIcon,
        IcLauncherBackground)
    return __AllIcons!!
  }
