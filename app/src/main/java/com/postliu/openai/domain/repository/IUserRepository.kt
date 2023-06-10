package com.postliu.openai.domain.repository

import com.postliu.openai.model.local.LocalUserProfileData
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    // 用户资料
    fun userProfile(): Flow<LocalUserProfileData>
}