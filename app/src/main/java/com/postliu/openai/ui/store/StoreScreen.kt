@file:OptIn(ExperimentalFoundationApi::class)

package com.postliu.openai.ui.store

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.postliu.openai.R
import com.postliu.openai.base.SystemBarTransparent
import com.postliu.openai.base.UIState.Companion.successData
import com.postliu.openai.model.local.LocalSortData
import com.postliu.openai.model.local.LocalStoreData
import com.postliu.openai.ui.theme.OpenAITheme
import com.postliu.openai.widgets.PagerGrid
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun StoreScreen(navigator: DestinationsNavigator) {
    val viewModel: StoreScreenViewModel = hiltViewModel()
    val tabList = viewModel.tabList
    val storeSort by viewModel.storeSort.collectAsStateWithLifecycle(null)
    val store = viewModel.storeList.collectAsLazyPagingItems()
    val lazyListState = if (store.itemCount > 0) viewModel.lazyListState else LazyListState()
    StorePage(
        lazyListState = lazyListState,
        storeSort = storeSort?.successData.orEmpty(),
        tabList = tabList,
        store = store,
        tabSelected = {
            viewModel.storeList(order = it.order)
        })
}

@Composable
fun StorePage(
    lazyListState: LazyListState,
    storeSort: List<LocalSortData>,
    tabList: List<StoreSort>,
    store: LazyPagingItems<LocalStoreData>,
    tabSelected: (StoreSort) -> Unit = {},
) {
    val sortList by remember(storeSort) { derivedStateOf { storeSort.chunked(10) } }
    var selectTab by remember { mutableStateOf<StoreSort>(StoreSort.Default) }
    val selectIndex by remember(selectTab) { derivedStateOf { tabList.indexOf(selectTab) } }
    val isSticky by snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.key == "Header" }.collectAsStateWithLifecycle(
        initialValue = false
    )
    SystemBarTransparent()
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.store_top_img_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.25f),
            contentScale = ContentScale.FillWidth,
            colorFilter = if (isSticky) ColorFilter.tint(Color.White) else null
        )
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(12.dp)) {
                StoreTopBar()
            }
            LazyColumn(
                content = {
                    item(key = "Pager") {
                        Box(
                            modifier = Modifier
                                .padding(start = 12.dp, end = 12.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    MaterialTheme.colors.background, RoundedCornerShape(12.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            StoreSortPager(sortList = sortList)
                        }
                    }
                    stickyHeader(key = "Header") {
                        TabRow(selectedTabIndex = selectIndex,
                            backgroundColor = MaterialTheme.colors.background,
                            indicator = {
                                TabRowDefaults.Indicator(
                                    modifier = Modifier
                                        .tabIndicatorOffset(it[selectIndex])
                                        .padding(horizontal = 50.dp),
                                    color = MaterialTheme.colors.primary
                                )
                            },
                            divider = {}) {
                            tabList.forEach {
                                Tab(selected = it == selectTab, onClick = {
                                    selectTab = it
                                    tabSelected(it)
                                }, modifier = Modifier.padding(vertical = 10.dp)) {
                                    Text(text = it.name, color = MaterialTheme.colors.onBackground)
                                }
                            }
                        }
                    }
                    items(store, key = { it.storeId }) {
                        it?.let { it1 -> StoreBaseInfo(data = it1) }
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                state = lazyListState,
                contentPadding = PaddingValues(bottom = 12.dp)
            )
        }
    }
}

@Composable
private fun StoreSortPager(sortList: List<List<LocalSortData>>) {
    PagerGrid(
        dataList = sortList,
        pageSpacing = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(186.dp),
        contentPaddingValues = PaddingValues(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(model = it.url, contentDescription = null, modifier = Modifier.size(44.dp))
            Text(text = it.name, fontSize = 12.sp)
        }
    }
}

@Composable
fun StoreTopBar(
    search: () -> Unit = {},
    cart: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier
            .weight(1f)
            .clip(CircleShape)
            .background(MaterialTheme.colors.background, CircleShape)
            .clickable { search() }
            .padding(horizontal = 14.dp, vertical = 9.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.store_search_icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(text = "搜索你感兴趣的商品", color = Color(0xFF919BAE), fontSize = 14.sp)
        }
        Image(imageVector = ImageVector.vectorResource(id = R.drawable.store_cart_icon),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(shape = CircleShape, color = Color.Transparent)
                .clickable { cart() })
    }
}

@Composable
fun StoreBaseInfo(data: LocalStoreData) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.store_top_end_background),
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(1.76f)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.FillWidth,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AsyncImage(
                    model = data.storeLogo,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data.storeName,
                        maxLines = 1,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.LocationOn,
                                contentDescription = null,
                                modifier = Modifier.size(13.dp),
                                tint = Color(0xFF6D6D6D)
                            )
                            Text(
                                buildAnnotatedString {
                                    append("商家距你:")
                                    append(data.distance)
                                }, fontSize = 13.sp, color = Color(0xFF6D6D6D), maxLines = 2
                            )
                        }
                        Button(
                            onClick = {},
                            shape = CircleShape,
                            modifier = Modifier.height(28.dp),
                            contentPadding = PaddingValues(vertical = 0.dp)
                        ) {
                            Text(text = "导航", fontSize = 13.sp)
                        }
                    }
                }
            }
            if (data.storeAddress.isNotEmpty()) {
                Text(
                    buildAnnotatedString {
                        append("地址:")
                        append(data.storeAddress)
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFFFF8F3), RoundedCornerShape(4.dp))
                        .padding(8.dp),
                    fontSize = 13.sp,
                    color = Color(0xFF9C5B2E)
                )
            }
        }
    }
}

@Preview
@Composable
fun StoreBaseInfoPreview() {
    OpenAITheme {
        StoreBaseInfo(data = LocalStoreData.default)
    }
}

@Preview
@Composable
fun StoreTopBarPreview() {
    OpenAITheme {
        StoreTopBar()
    }
}