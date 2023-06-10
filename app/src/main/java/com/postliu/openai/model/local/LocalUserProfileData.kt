package com.postliu.openai.model.local

/**
 * 用户资料信息
 *
 * @property id
 * @property name 昵称
 * @property realName 真实姓名
 * @property avatar 头像
 * @property sex 性别
 * @property birthDay 生日
 * @property mobile 手机号码
 * @property passNumber 通证余额
 * @property greenPoint 绿色积分
 * @property consumePoint 消费积分
 * @property exchangePoint 兑换积分
 * @property levelName 等级名称
 * @property label 职称标签
 * @constructor Create empty Local user profile data
 */
data class LocalUserProfileData(
    val id: Int,
    val name: String,
    val realName: String,
    val avatar: String,
    val sex: Int,
    val birthDay: String,
    val mobile: String,
    val passNumber: String,
    val greenPoint: String,
    val consumePoint: String,
    val exchangePoint: String,
    val levelName: String,
    val label: List<LocalUserLabelData>,
) {
    constructor() : this(0, "", "", "", 0, "", "", "0.00", "0.00", "0.00", "0.00", "", emptyList())
}
