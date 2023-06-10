@file:OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)

package com.postliu.openai.ui.user

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.postliu.openai.R
import com.postliu.openai.base.SystemBarTransparent
import com.postliu.openai.model.local.LocalOrderType
import com.postliu.openai.model.local.LocalUserLabelData
import com.postliu.openai.model.local.LocalUserNavigationType
import com.postliu.openai.model.local.LocalUserProfileData
import com.postliu.openai.ui.theme.OpenAITheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun UserScreen(navigator: DestinationsNavigator) {
    val viewModel: UserScreenViewModel = hiltViewModel()
    val user by viewModel.user.collectAsStateWithLifecycle()
    UserPage(user)
}

@Composable
fun UserPage(user: LocalUserProfileData) {
    SystemBarTransparent()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8F9))
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_top_img_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        LazyColumn(
            content = {
                stickyHeader(key = "profile") {
                    Box(
                        modifier = Modifier
                            .systemBarsPadding()
                            .padding(10.dp)
                    ) {
                        UserBaseInfo(
                            avatar = user.avatar,
                            name = user.name,
                            level = user.levelName,
                            label = user.label
                        )
                    }
                }
                item(key = "passWallet") {
                    Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 6.dp)) {
                        UserPassWallet(user.passNumber)
                    }
                }
                item(key = "points") {
                    Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 6.dp)) {
                        UserMultiplePoints(
                            greenPoints = user.greenPoint,
                            consumptionPoints = user.consumePoint,
                            redeemPoints = user.exchangePoint
                        )
                    }
                }
                item(key = "order") {
                    Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 6.dp)) {
                        UserOrderNavigation()
                    }
                }
                items(
                    listOf(
                        listOf(
                            LocalUserNavigationType.NameVerification,
                            LocalUserNavigationType.InviteFriends,
                            LocalUserNavigationType.MyTeam
                        ),
                        listOf(
                            LocalUserNavigationType.AccountSettings,
                            LocalUserNavigationType.MerchantManager,
                            LocalUserNavigationType.MerchantSettlement
                        )
                    )
                ) {
                    Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 6.dp)) {
                        UserNavigation(dataList = it)
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun UserBaseInfo(
    avatar: String = "",
    name: String = "测试昵称",
    level: String = "LV1",
    label: List<LocalUserLabelData> = LocalUserLabelData.default,
    setting: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = avatar,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colors.onPrimary, CircleShape)
                .background(Color.Transparent, CircleShape),
            error = painterResource(id = R.drawable.ic_user_avatar_default_logo)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .basicMarquee(iterations = Int.MAX_VALUE, delayMillis = 0, spacing = MarqueeSpacing(24.dp))
                ) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.onPrimary,
                    )
                    if (level.isNotEmpty()) {
                        Text(
                            text = level, modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.linearGradient(listOf(Color(0xFFF97133), Color(0xFFF99D33))),
                                    shape = CircleShape
                                )
                                .padding(horizontal = 8.dp, vertical = 1.dp),
                            fontSize = 10.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
                IconButton(onClick = { setting() }, modifier = Modifier.size(18.dp)) {
                    Icon(
                        imageVector = Icons.Sharp.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
            FlowRow(
                maxItemsInEachRow = 2, modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                label.forEach {
                    Box(
                        modifier = Modifier
                            .height(32.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = it.name,
                            color = Color(it.nameColor),
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .clip(
                                    RoundedCornerShape(
                                        topStartPercent = 0,
                                        topEndPercent = 50,
                                        bottomStartPercent = 0,
                                        bottomEndPercent = 50
                                    )
                                )
                                .background(Color(it.backgroundColor))
                                .padding(start = 20.dp, end = 8.dp, bottom = 1.dp, top = 1.dp),
                            fontSize = 12.sp,

                            )
                        Image(
                            painter = painterResource(id = it.icon),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserPassWallet(balance: String = "0.00") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_pass_wallet_img_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 12.sp, color = Color(0xFFB25E66))) {
                    append("通证钱包")
                }
                append("\n")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 26.sp)) {
                    append(balance)
                }
            }, modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .align(Alignment.CenterStart), lineHeight = TextUnit(2f, TextUnitType.Em)
        )
    }
}

@Composable
fun UserMultiplePoints(
    greenPoints: String = "0.00",
    consumptionPoints: String = "0.00",
    redeemPoints: String = "0.00",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)) {
                    append(greenPoints)
                }
                append("\n")
                withStyle(SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Light)) {
                    append("绿色积分")
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight(), thickness = 1.dp
        )
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)) {
                    append(consumptionPoints)
                }
                append("\n")
                withStyle(SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Light)) {
                    append("消费积分")
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight(), thickness = 1.dp
        )
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)) {
                    append(redeemPoints)
                }
                append("\n")
                withStyle(SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Light)) {
                    append("兑换积分")
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun UserOrderNavigation(
    orderNavigation: List<LocalOrderType> = LocalOrderType.default1
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                MaterialTheme.colors.background,
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "我的订单")
            Text(text = "查看全部订单", fontSize = 10.sp)
        }
        FlowRow(
            maxItemsInEachRow = 4,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            orderNavigation.forEach {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(text = it.name, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun UserNavigation(dataList: List<LocalUserNavigationType>, onClick: (LocalUserNavigationType) -> Unit = {}) {
    FlowRow(
        maxItemsInEachRow = 3, modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                MaterialTheme.colors.background,
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        dataList.forEach {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { onClick(it) }
                    .background(MaterialTheme.colors.background, MaterialTheme.shapes.small)
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = it.icon),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp)
                )
                Text(text = it.name, fontSize = 12.sp)
            }
        }
    }
}

@Preview
@Composable
fun UserBaseInfoPreview() {
    OpenAITheme {
        UserBaseInfo()
    }
}

@Preview
@Composable
fun UserPassWalletPreview() {
    OpenAITheme {
        UserPassWallet()
    }
}

@Preview
@Composable
fun UserMultiplePointsPreview() {
    OpenAITheme {
        UserMultiplePoints()
    }
}

@Preview
@Composable
fun UserOrderNavigationPreview() {
    OpenAITheme {
        UserOrderNavigation()
    }
}

@Preview
@Composable
fun UserNavigationPreview() {
    OpenAITheme {
        UserNavigation(
            dataList = listOf(
                LocalUserNavigationType.NameVerification,
                LocalUserNavigationType.InviteFriends,
                LocalUserNavigationType.MyTeam
            )
        )
    }
}