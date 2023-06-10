package com.postliu.openai.model.network


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkUserProfileData(
    @SerializedName("member_info")
    val memberInfo: MemberInfo?
) {
    @Keep
    data class MemberInfo(
        @SerializedName("available_predeposit")
        val availablePredeposit: String?,
        @SerializedName("available_rc_balance")
        val availableRcBalance: String?,
        @SerializedName("bonus")
        val bonus: String?,
        @SerializedName("consume_point")
        val consumePoint: String?,
        @SerializedName("exchange_account")
        val exchangeAccount: Any?,
        @SerializedName("exchange_point")
        val exchangePoint: String?,
        @SerializedName("fire_value")
        val fireValue: String?,
        @SerializedName("freeze_predeposit")
        val freezePredeposit: String?,
        @SerializedName("freeze_rc_balance")
        val freezeRcBalance: String?,
        @SerializedName("green_point")
        val greenPoint: String?,
        @SerializedName("inform_allow")
        val informAllow: Int?,
        @SerializedName("inviter_id")
        val inviterId: Int?,
        @SerializedName("inviter_state")
        val inviterState: Int?,
        @SerializedName("is_allowtalk")
        val isAllowtalk: Int?,
        @SerializedName("is_buylimit")
        val isBuylimit: Int?,
        @SerializedName("is_partner_area")
        val isPartnerArea: Int?,
        @SerializedName("is_partner_city")
        val isPartnerCity: Int?,
        @SerializedName("is_partner_enterprise")
        val isPartnerEnterprise: Int?,
        @SerializedName("is_partner_lianc")
        val isPartnerLianc: Int?,
        @SerializedName("is_partner_store")
        val isPartnerStore: Int?,
        @SerializedName("level_id")
        val levelId: Int?,
        @SerializedName("level_name")
        val levelName: String?,
        @SerializedName("member_addtime")
        val memberAddtime: Int?,
        @SerializedName("member_areaid")
        val memberAreaid: Any?,
        @SerializedName("member_areainfo")
        val memberAreainfo: String?,
        @SerializedName("member_auth_state")
        val memberAuthState: Int?,
        @SerializedName("member_avatar")
        val memberAvatar: String?,
        @SerializedName("member_birthday")
        val memberBirthday: String?,
        @SerializedName("member_cityid")
        val memberCityid: Any?,
        @SerializedName("member_email")
        val memberEmail: Any?,
        @SerializedName("member_emailbind")
        val memberEmailbind: Int?,
        @SerializedName("member_exppoints")
        val memberExppoints: Int?,
        @SerializedName("member_grade")
        val memberGrade: Int?,
        @SerializedName("member_grade_admin")
        val memberGradeAdmin: Int?,
        @SerializedName("member_id")
        val memberId: Int?,
        @SerializedName("member_idcard")
        val memberIdcard: String?,
        @SerializedName("member_idcard_image1")
        val memberIdcardImage1: String?,
        @SerializedName("member_idcard_image1_url")
        val memberIdcardImage1Url: String?,
        @SerializedName("member_idcard_image2")
        val memberIdcardImage2: String?,
        @SerializedName("member_idcard_image2_url")
        val memberIdcardImage2Url: String?,
        @SerializedName("member_idcard_image3")
        val memberIdcardImage3: String?,
        @SerializedName("member_idcard_image3_url")
        val memberIdcardImage3Url: String?,
        @SerializedName("member_login_ip")
        val memberLoginIp: Any?,
        @SerializedName("member_loginnum")
        val memberLoginnum: Int?,
        @SerializedName("member_logintime")
        val memberLogintime: Int?,
        @SerializedName("member_mobile")
        val memberMobile: String?,
        @SerializedName("member_mobilebind")
        val memberMobilebind: Int?,
        @SerializedName("member_name")
        val memberName: String?,
        @SerializedName("member_nickname")
        val memberNickname: String?,
        @SerializedName("member_old_login_ip")
        val memberOldLoginIp: Any?,
        @SerializedName("member_old_logintime")
        val memberOldLogintime: Int?,
        @SerializedName("member_partner")
        val memberPartner: Int?,
        @SerializedName("member_points")
        val memberPoints: Int?,
        @SerializedName("member_privacy")
        val memberPrivacy: Any?,
        @SerializedName("member_provinceid")
        val memberProvinceid: Any?,
        @SerializedName("member_qq")
        val memberQq: Any?,
        @SerializedName("member_qqinfo")
        val memberQqinfo: Any?,
        @SerializedName("member_qqopenid")
        val memberQqopenid: Any?,
        @SerializedName("member_sex")
        val memberSex: Int?,
        @SerializedName("member_signin_days_cycle")
        val memberSigninDaysCycle: Int?,
        @SerializedName("member_signin_days_series")
        val memberSigninDaysSeries: Int?,
        @SerializedName("member_signin_days_total")
        val memberSigninDaysTotal: Int?,
        @SerializedName("member_signin_time")
        val memberSigninTime: Int?,
        @SerializedName("member_sinainfo")
        val memberSinainfo: Any?,
        @SerializedName("member_sinaopenid")
        val memberSinaopenid: Any?,
        @SerializedName("member_state")
        val memberState: Int?,
        @SerializedName("member_truename")
        val memberTruename: String?,
        @SerializedName("member_ww")
        val memberWw: Any?,
        @SerializedName("member_wxinfo")
        val memberWxinfo: Any?,
        @SerializedName("member_wxopenid")
        val memberWxopenid: String?,
        @SerializedName("member_wxunionid")
        val memberWxunionid: String?,
        @SerializedName("order_noeval_count")
        val orderNoevalCount: Int?,
        @SerializedName("order_nopay_count")
        val orderNopayCount: Int?,
        @SerializedName("order_noreceipt_count")
        val orderNoreceiptCount: Int?,
        @SerializedName("order_noship_count")
        val orderNoshipCount: Int?,
        @SerializedName("order_points")
        val orderPoints: Int?,
        @SerializedName("order_refund_count")
        val orderRefundCount: Int?,
        @SerializedName("pass_address")
        val passAddress: String?,
        @SerializedName("pass_count")
        val passCount:String?,
        @SerializedName("red_envelopes")
        val redEnvelopes: String?,
        @SerializedName("reward_point")
        val rewardPoint: String?,
        @SerializedName("shares")
        val shares: String?,
        @SerializedName("store_id")
        val storeId: Int?,
        @SerializedName("team")
        val team: String?,
        @SerializedName("total_reward")
        val totalReward: String?,
        @SerializedName("voucher_count")
        val voucherCount: Int?
    )
}