package com.postliu.openai.ui.partner_area

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.postliu.openai.base.SystemBarColor
import com.postliu.openai.model.local.LocalHomeGoodsData
import com.postliu.openai.ui.theme.OpenAITheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PartnerAreaScreen(
    navigator: DestinationsNavigator,
    name: String,
    type: Int,
) {
    val viewModel: PartnerAreaScreenViewModel = hiltViewModel()
    val goods = type.let { viewModel.partnerAreaGoods(it).collectAsLazyPagingItems() }
    PartnerAreaScreen(title = name, back = { navigator.popBackStack() }, goods = goods)
}

@Composable
private fun PartnerAreaScreen(
    title: String = "合伙人",
    back: () -> Unit = {},
    goods: LazyPagingItems<LocalHomeGoodsData>?,
) {
    SystemBarColor(MaterialTheme.colors.background)
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBar(title = {
            Text(text = title)
        }, navigationIcon = {
            IconButton(onClick = { back() }) {
                Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = null)
            }
        }, backgroundColor = MaterialTheme.colors.background, elevation = 0.dp)
        goods?.let { lazyPagingItems ->
            val isEmpty = lazyPagingItems.itemSnapshotList.isEmpty()
            val refreshState = lazyPagingItems.loadState.refresh
            if (isEmpty) {
                PartnerEmpty(refreshState, lazyPagingItems)
            } else {
                PartnerAreaGoodsGrid(lazyPagingItems, refreshState, goods)

            }
        }
    }
}

@Composable
private fun PartnerAreaGoodsGrid(
    lazyPagingItems: LazyPagingItems<LocalHomeGoodsData>,
    refreshState: LoadState,
    goods: LazyPagingItems<LocalHomeGoodsData>?
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        content = {
            when (val appendState = lazyPagingItems.loadState.append) {
                is LoadState.Error -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = { lazyPagingItems.retry() }) {
                                Text(text = appendState.error.message.orEmpty()
                                    .ifEmpty { "加载失败,点击重试" })
                            }
                        }
                    }
                }

                is LoadState.Loading -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    }
                }

                is LoadState.NotLoading -> {
                    if (refreshState.endOfPaginationReached) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "没有更多数据了哦~")
                            }
                        }
                    }
                }
            }
            goods?.let {
                items(
                    count = goods.itemCount,
                    key = goods.itemKey { it.id },
                    contentType = goods.itemContentType { "goods" }) { index ->
                    goods[index]?.let { it1 -> PartnerAreaGoods(data = it1) }
                }
            }
        },
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun PartnerEmpty(
    refreshState: LoadState,
    lazyPagingItems: LazyPagingItems<LocalHomeGoodsData>
) {
    when (refreshState) {
        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }

        is LoadState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = { lazyPagingItems.refresh() }) {
                    Text(
                        text = refreshState.error.message.orEmpty().ifEmpty { "加载失败,点击重试" })
                }
            }
        }

        is LoadState.NotLoading -> {
            if (lazyPagingItems.itemSnapshotList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "没有数据哦~")
                }
            }
        }
    }
}

@Composable
private fun PartnerAreaGoods(data: LocalHomeGoodsData, onClick: (LocalHomeGoodsData) -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(294.dp)
        .clip(RoundedCornerShape(4.dp))
        .clickable { onClick(data) }
        .background(MaterialTheme.colors.background, RoundedCornerShape(4.dp)),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        AsyncImage(
            model = data.url,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = data.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Divider(thickness = 12.dp, color = MaterialTheme.colors.background)
        Text(text = buildAnnotatedString {
            withStyle(SpanStyle(fontSize = 12.sp)) {
                append("¥")
            }
            withStyle(SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                append(data.price)
            }
        }, color = MaterialTheme.colors.primary, modifier = Modifier.padding(horizontal = 12.dp))
    }
}

@Preview(
    device = Devices.PIXEL_3,
    showBackground = true,
    backgroundColor = 0xffffffff,
    widthDp = 375,
    heightDp = 812
)
@Composable
fun PartnerAreaGoodsPreview() {
    OpenAITheme {
        PartnerAreaGoods(data = LocalHomeGoodsData.default)
    }
}