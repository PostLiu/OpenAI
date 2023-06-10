package com.postliu.openai.domain.usecase

import com.postliu.openai.base.UIState
import com.postliu.openai.model.local.LocalSortData
import kotlinx.coroutines.flow.Flow

interface IGetStoreSortUseCase {

    operator fun invoke(longitude: String?, latitude: String?): Flow<UIState<List<LocalSortData>>>
}