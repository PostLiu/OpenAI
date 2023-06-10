package com.postliu.openai.model.local

import com.postliu.openai.R

sealed class LocalUserNavigationType(val icon: Int, val name: String) {
    object NameVerification : LocalUserNavigationType(R.drawable.user_name_verification_icon, "实名验证")
    object InviteFriends : LocalUserNavigationType(R.drawable.user_invite_friends_icon, "邀请好友")
    object MyTeam : LocalUserNavigationType(R.drawable.user_my_team_icon, "我的团队")
    object AccountSettings : LocalUserNavigationType(R.drawable.user_account_settings_icon, "账户设置")
    object MerchantManager : LocalUserNavigationType(R.drawable.user_merchant_manager_icon, "商家管理")
    object MerchantSettlement : LocalUserNavigationType(R.drawable.user_merchant_settlement_icon, "商家入驻")
}