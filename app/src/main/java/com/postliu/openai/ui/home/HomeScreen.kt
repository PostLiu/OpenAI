@file:OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)

package com.postliu.openai.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.lt.compose_views.banner.Banner
import com.lt.compose_views.banner.rememberBannerState
import com.postliu.openai.Icons
import com.postliu.openai.R
import com.postliu.openai.base.SystemBarTransparent
import com.postliu.openai.base.UIState
import com.postliu.openai.base.UIState.Companion.doCatch
import com.postliu.openai.base.UIState.Companion.doFailed
import com.postliu.openai.base.UIState.Companion.doStart
import com.postliu.openai.base.UIState.Companion.doSuccess
import com.postliu.openai.base.items
import com.postliu.openai.icons.GoodsPointIcon
import com.postliu.openai.model.local.HomeArea
import com.postliu.openai.model.local.LocalBannerData
import com.postliu.openai.model.local.LocalHome
import com.postliu.openai.model.local.LocalHomeAreaData
import com.postliu.openai.model.local.LocalHomeGoodsData
import com.postliu.openai.model.local.LocalSortData
import com.postliu.openai.ui.destinations.LoginScreenDestination
import com.postliu.openai.widgets.PagerGrid
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    SystemBarTransparent()
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val homeData by viewModel.homeData.collectAsStateWithLifecycle()
    val goods = viewModel.homeGoods.collectAsLazyPagingItems()
    val gridState = if (goods.itemCount > 0) viewModel.gridState else LazyGridState()
    HomeRefresh(homeData = homeData, gridState = gridState, goods = goods, search = {
        navigator.navigate(LoginScreenDestination)
    }, refresh = {
        viewModel.dispatch(HomeAction.Refresh)
        goods.refresh()
    })
}

@Composable
private fun HomeRefresh(
    homeData: UIState<LocalHome>,
    gridState: LazyGridState,
    goods: LazyPagingItems<LocalHomeGoodsData>,
    refresh: () -> Unit = {},
    search: () -> Unit = {},
) {
    var isRefresh by remember { mutableStateOf(false) }
    val refreshState = rememberPullRefreshState(refreshing = isRefresh, onRefresh = {
        refresh()
    })
    isRefresh = homeData is UIState.Loading
    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .pullRefresh(refreshState),
        contentAlignment = Alignment.Center
    ) {
        homeData.doStart {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
            )
        }.doCatch {
            Button(onClick = {
                refresh()
            }, modifier = Modifier.padding(24.dp)) {
                Text(text = it.message.orEmpty())
            }
        }.doFailed {
            Button(onClick = {
                refresh()
            }, modifier = Modifier.padding(24.dp)) {
                Text(text = it)
            }
        }.doSuccess {
            val bannerList = it.bannerList
            val sortList by remember(it.sortList) { derivedStateOf { it.sortList.chunked(10) } }
            val news = it.news
            val areas = it.areas
            HomeScreen(
                gridState = gridState,
                bannerList = bannerList,
                sortList = sortList,
                news = news,
                area = areas,
                goods = goods,
                search = { search() }
            )
        }
        PullRefreshIndicator(
            refreshing = isRefresh,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun HomeScreen(
    gridState: LazyGridState,
    bannerList: List<LocalBannerData>,
    sortList: List<List<LocalSortData>>,
    news: List<LocalHomeGoodsData>,
    area: List<LocalHomeAreaData>,
    goods: LazyPagingItems<LocalHomeGoodsData>,
    search: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopSearchBar {
            search()
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp), state = gridState, content = {
                if (bannerList.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        HomeBanner(dataList = bannerList)
                    }
                }
                if (sortList.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        PagerGrid(modifier = Modifier.height(145.dp), dataList = sortList) {
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = it.url,
                                    contentDescription = null,
                                    modifier = Modifier.size(44.dp)
                                )
                                Text(
                                    text = it.name,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
                if (news.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        HomeNewGoods(goods = news)
                    }
                }
                if (area.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 7.dp),
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            area.forEach {
                                HomeArea(it)
                            }
                        }
                    }
                }
                items(goods) {
                    it?.let { HomeSimpleGoods(data = it) }
                }
                val state = goods.loadState
                if (state.append is LoadState.Loading) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    }
                }
                if (state.append is LoadState.Error) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                goods.retry()
                            }) {
                                Text(text = (state.append as LoadState.Error).error.message.orEmpty())
                            }
                        }
                    }
                }
                if (state.append is LoadState.NotLoading) {
                    if (state.append.endOfPaginationReached) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "没有更多数据了~")
                            }
                        }
                    }
                }
            }, horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(5.dp)
        )
    }
}

@Composable
private fun RowScope.HomeArea(data: LocalHomeAreaData) {
    Box(modifier = Modifier.Companion.weight(1f)) {
        AsyncImage(
            model = data.background,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 16.sp, color = when (data.area) {
                            is HomeArea.Partner -> Color(0xFF5C407D)
                            is HomeArea.Exchange -> Color(0xFF7C3434)
                        }, fontWeight = FontWeight.Bold
                    )
                ) {
                    append(data.name)
                }
                append("\n")
                withStyle(
                    SpanStyle(
                        fontSize = 12.sp, color = when (data.area) {
                            is HomeArea.Partner -> Color(0xFF746787)
                            is HomeArea.Exchange -> Color(0xFF76727C)
                        }
                    )
                ) {
                    append(data.subtitle)
                }
            },
            lineHeight = TextUnit(20f, TextUnitType.Sp),
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
private fun HomeNewGoods(goods: List<LocalHomeGoodsData>) {
    if (goods.isEmpty()) return
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = R.drawable.home_news_background,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        FlowRow(
            maxItemsInEachRow = 4,
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .align(Alignment.BottomCenter)
        ) {
            AsyncImage(
                model = R.drawable.home_news_title_image,
                contentDescription = null,
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
            goods.forEach {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = it.url,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontSize = 10.sp)) {
                                append("¥")
                            }
                            withStyle(SpanStyle(fontSize = 16.sp)) {
                                append(it.price)
                            }
                        },
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeSimpleGoods(data: LocalHomeGoodsData) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(2.dp))
    ) {
        AsyncImage(
            model = data.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = data.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 10.sp, color = MaterialTheme.colors.primary)) {
                    append("¥")
                }
                withStyle(SpanStyle(fontSize = 18.sp, color = MaterialTheme.colors.primary)) {
                    append(data.price)
                }
            })
            if (data.point.isNotEmpty() && data.point != "0.00" && data.point != "0") {
                GoodsPoint(data)
            }
        }
    }
}

@Composable
private fun GoodsPoint(it: LocalHomeGoodsData) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color(0xFFFC9835))
            .padding(start = 4.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        AsyncImage(
            model = Icons.GoodsPointIcon,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = it.point, modifier = Modifier, fontSize = 12.sp, color = Color(0xFF8B4500),
        )
    }
}

@Composable
private fun HomeTopSearchBar(onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Row(modifier = Modifier
            .weight(1f)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Color(0xFFF0F3F8))
            .padding(vertical = 6.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                contentDescription = null
            )
            Text(
                text = "搜索你感兴趣的商品",
                color = Color(0xFF919BAE),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun HomeBanner(dataList: List<LocalBannerData>) {
    val bannerState = rememberBannerState()
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Banner(
            pageCount = dataList.size, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            bannerState = bannerState
        ) {
            BannerItem(dataList[index].url)
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = dataList.size,
            pageIndexMapping = { bannerState.getCurrSelectIndex() },
            activeColor = Color.Red,
            inactiveColor = Color.Black,
            indicatorWidth = 6.dp,
            indicatorHeight = 6.dp,
            spacing = 4.dp,
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.BottomEnd)
                .wrapContentSize()
                .clip(CircleShape)
                .background(Color(0x50000000), CircleShape)
                .padding(8.dp)
        )
    }

}

@Composable
private fun SelectedIndicator() {
    Box(
        modifier = Modifier
            .size(6.dp)
            .clip(CircleShape)
            .background(Color.Red, CircleShape)
    )
}

@Composable
private fun NormalIndicator() {
    Box(
        modifier = Modifier
            .size(6.dp)
            .clip(CircleShape)
            .background(Color.Black, CircleShape)
    )
}

@Composable
private fun BannerItem(imageUrl: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
        )
    }
}