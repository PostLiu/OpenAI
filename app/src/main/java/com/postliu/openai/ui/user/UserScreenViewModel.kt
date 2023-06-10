package com.postliu.openai.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postliu.openai.domain.repository.UserRepository
import com.postliu.openai.model.local.LocalUserProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val user = repository.userProfile().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        LocalUserProfileData()
    )
}
