package com.postliu.openai.ui.store

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.postliu.openai.base.UIState
import com.postliu.openai.domain.usecase.IGetStoreListUseCase
import com.postliu.openai.domain.usecase.IGetStoreSortUseCase
import com.postliu.openai.model.local.LocalSortData
import com.postliu.openai.model.local.LocalStoreData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreScreenViewModel @Inject constructor(
    private val storeSortUseCase: IGetStoreSortUseCase,
    private val storeListUseCase: IGetStoreListUseCase
) : ViewModel() {

    val lazyListState = LazyListState()

    val isSticky
        get() = snapshotFlow {
            lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.key == "Header"
        }

    var latitude: String? = null

    var longitude: String? = null

    val tabList = listOf(StoreSort.Default, StoreSort.Hot, StoreSort.Distance)

    private val mStoreSort = MutableStateFlow<UIState<List<LocalSortData>>>(UIState.Loading)
    val storeSort = mStoreSort.asStateFlow()

    private val mStoreList = MutableStateFlow<PagingData<LocalStoreData>>(PagingData.empty())
    val storeList = mStoreList.asStateFlow()

    init {
        dispatch(StoreAction.Initial)
    }

    fun dispatch(action: StoreAction) {
        when (action) {
            StoreAction.Initial -> {
                storeSort(longitude, latitude)
                storeList()
            }

            StoreAction.Refresh -> {
                storeSort(longitude, latitude)
            }
        }
    }

    private fun storeSort(
        longitude: String? = null,
        latitude: String? = null
    ) = viewModelScope.launch {
        storeSortUseCase(longitude, latitude).collectLatest { mStoreSort.emit(it) }
    }

    fun storeList(
        cityName: String? = "广州市",
        areaId: String? = "",
        latitude: String? = "23.15792",
        longitude: String? = "113.27324",
        order: String? = "",
        sortId: Int? = 0
    ) = viewModelScope.launch {
        storeListUseCase(cityName, areaId, latitude, longitude, order, sortId).cachedIn(
            viewModelScope
        ).collectLatest {
            mStoreList.emit(it)
        }
    }
}

sealed class StoreSort(val name: String, val order: String) {
    object Default : StoreSort("默认排序", "")
    object Distance : StoreSort("距离优先", "distance asc")
    object Hot : StoreSort("热门优先", "store_sales asc")
}

sealed interface StoreAction {
    object Initial : StoreAction
    object Refresh : StoreAction
}