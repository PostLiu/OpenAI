package com.postliu.openai.model.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NetworkLoginData(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("token")
    val token: String?
) {
    @Keep
    data class Info(
        @SerializedName("member_auth_state")
        val memberAuthState: Int?,
        @SerializedName("member_avatar")
        val memberAvatar: String?,
        @SerializedName("member_birthday")
        val memberBirthday: String?,
        @SerializedName("member_email")
        val memberEmail: String?,
        @SerializedName("member_id")
        val memberId: Int?,
        @SerializedName("member_idcard_image1_url")
        val memberIdcardImage1Url: String?,
        @SerializedName("member_idcard_image2_url")
        val memberIdcardImage2Url: String?,
        @SerializedName("member_idcard_image3_url")
        val memberIdcardImage3Url: String?,
        @SerializedName("member_mobile")
        val memberMobile: Any?,
        @SerializedName("member_name")
        val memberName: String?,
        @SerializedName("member_nickname")
        val memberNickname: Any?,
        @SerializedName("member_points")
        val memberPoints: Int?,
        @SerializedName("member_qq")
        val memberQq: Any?,
        @SerializedName("member_sex")
        val memberSex: Any?,
        @SerializedName("member_truename")
        val memberTruename: Any?,
        @SerializedName("member_ww")
        val memberWw: Any?
    )
}