@file:OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)

package com.postliu.openai.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> PagerGrid(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
    dataList: List<List<T>>,
    pageSpacing: Dp = 0.dp,
    maxItemsInEachRow: Int = 5,
    contentPaddingValues: PaddingValues = PaddingValues(),
    child: @Composable (RowScope.(T) -> Unit)
) {
    HorizontalPager(
        pageCount = dataList.size, state = pagerState,
        modifier = modifier
            .padding(contentPaddingValues),
        pageSpacing = pageSpacing,
    ) { page ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(maxItemsInEachRow),
            content = {
                items(dataList[page]) {
                    Row {
                        child(this, it)
                    }
                }
            },
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
    }
}