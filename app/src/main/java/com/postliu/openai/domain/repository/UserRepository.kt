package com.postliu.openai.domain.repository

import com.postliu.openai.R
import com.postliu.openai.api.ApiService
import com.postliu.openai.model.local.LocalUserLabelData
import com.postliu.openai.model.local.LocalUserProfileData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) : IUserRepository {

    override fun userProfile(): Flow<LocalUserProfileData> {
        return flow {
            emit(apiService.userProfile(emptyMap()).successData)
        }.map {
            it?.memberInfo?.let { info ->
                LocalUserProfileData(
                    id = info.memberId ?: 0,
                    name = info.memberNickname ?: info.memberName ?: "",
                    sex = info.memberSex ?: 0,
                    realName = info.memberTruename.orEmpty(),
                    avatar = info.memberAvatar.orEmpty(),
                    birthDay = info.memberBirthday.orEmpty(),
                    mobile = info.memberMobile.orEmpty(),
                    passNumber = info.passCount ?: "0.00",
                    greenPoint = info.greenPoint ?: "0.00",
                    consumePoint = info.consumePoint ?: "0.00",
                    exchangePoint = info.exchangePoint ?: "0.00",
                    levelName = info.levelName.orEmpty(),
                    label = listOfNotNull(
                        if (info.isPartnerCity == 1) LocalUserLabelData(
                            name = (info.memberAreainfo ?: "市").plus("合伙人"),
                            icon = R.drawable.user_label_1,
                            nameColor = 0xFF43915E,
                            backgroundColor = 0xFFEAFFE1
                        ) else null,
                        if (info.isPartnerArea == 1) LocalUserLabelData(
                            name = (info.memberAreainfo ?: "区").plus("合伙人"),
                            icon = R.drawable.user_label_2,
                            nameColor = 0xFF926B12,
                            backgroundColor = 0xFFFFE9B6
                        ) else null,
                        if (info.isPartnerStore == 1) LocalUserLabelData(
                            name = "商家合伙人",
                            icon = R.drawable.user_label_3,
                            nameColor = 0xFF5F6C98,
                            backgroundColor = 0xFFE1E8FF
                        ) else null,
                        if (info.isPartnerEnterprise == 1) LocalUserLabelData(
                            name = "企业合伙人",
                            icon = R.drawable.user_label_4,
                            nameColor = 0xFF7852AA,
                            backgroundColor = 0xFFEDE1FF
                        ) else null,
                        if (info.isPartnerLianc == 1) LocalUserLabelData(
                            name = "联合创始人",
                            icon = R.drawable.user_label_5,
                            nameColor = 0xFFAA5252,
                            backgroundColor = 0xFFFFE1E1
                        ) else null
                    )
                )
            } ?: LocalUserProfileData()
        }.flowOn(Dispatchers.IO)
    }
}