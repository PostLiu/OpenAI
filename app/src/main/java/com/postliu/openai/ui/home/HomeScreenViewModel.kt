package com.postliu.openai.ui.home

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.postliu.openai.base.UIState
import com.postliu.openai.domain.usecase.IGetHomeConfigUseCase
import com.postliu.openai.domain.usecase.IGetHomeGoodsUseCase
import com.postliu.openai.model.local.LocalHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    homeGoodsUseCase: IGetHomeGoodsUseCase,
    private val homeConfigUseCase: IGetHomeConfigUseCase,
) : ViewModel() {

    val gridState: LazyGridState = LazyGridState()

    private val mHomeData = MutableStateFlow<UIState<LocalHome>>(UIState.Loading)
    val homeData = mHomeData.asStateFlow()

    init {
        dispatch(HomeAction.Initial)
    }

    fun dispatch(action: HomeAction) {
        when (action) {
            is HomeAction.Initial -> {
                initHomeData()
            }

            is HomeAction.Refresh -> {
                initHomeData()
            }
        }
    }

    private fun initHomeData() = viewModelScope.launch {
        homeConfigUseCase().catch {
            mHomeData.emit(UIState.Throw(it))
        }.collectLatest {
            mHomeData.emit(it)
        }
    }

    val homeGoods = homeGoodsUseCase().cachedIn(viewModelScope)
}

sealed interface HomeAction {
    object Refresh : HomeAction
    object Initial : HomeAction
}