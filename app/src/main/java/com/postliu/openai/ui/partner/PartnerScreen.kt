package com.postliu.openai.ui.partner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.postliu.openai.R
import com.postliu.openai.base.SystemBarTransparent
import com.postliu.openai.ui.destinations.PartnerAreaScreenDestination
import com.postliu.openai.ui.theme.OpenAITheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PartnerScreen(navigator: DestinationsNavigator) {
    val viewModel: PartnerScreenViewModel = hiltViewModel()
    val partnerList = viewModel.defaultList
    PartnerPage(partnerList = partnerList, onClick = {
        when (it) {
            is PartnerAction.Company -> {
                navigator.navigate(PartnerAreaScreenDestination(name = it.title, type = it.id))
            }

            is PartnerAction.Business -> {
                navigator.navigate(PartnerAreaScreenDestination(name = it.title, type = it.id))
            }
        }
    })
}

@Composable
private fun PartnerPage(partnerList: List<PartnerAction>, onClick: (action: PartnerAction) -> Unit = {}) {
    SystemBarTransparent(statusBarDarkIcons = false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6495A))
    ) {
        AsyncImage(
            model = R.drawable.partner_img_background,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        LazyColumn(
            content = {
                items(partnerList, key = { it.id }) {
                    PartnerCard(
                        leadingIcon = it.leadingIcon,
                        trailingIcon = it.trailingIcon,
                        title = it.title,
                    ) {
                        onClick(it)
                    }
                }
            },
            modifier = Modifier
                .padding(top = 240.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )

    }
}

@Composable
private fun PartnerCard(
    shape: Shape = RoundedCornerShape(8.dp),
    paddingValues: PaddingValues = PaddingValues(top = 8.dp, bottom = 12.dp, start = 15.dp, end = 15.dp),
    leadingIcon: Int,
    trailingIcon: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(shape)
        .border(
            width = 4.dp,
            brush = Brush.radialGradient(colors = listOf(Color(0x25FFFFFF), Color(0x25FFFFFF))),
            shape = shape
        )
        .background(shape = shape, color = Color.White)
        .clickable { onClick() }
        .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        AsyncImage(model = leadingIcon, contentDescription = null, modifier = Modifier.size(120.dp))
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        AsyncImage(model = trailingIcon, contentDescription = null, modifier = Modifier.size(16.dp))
    }
}

@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun PartnerScreenPreview() {
    OpenAITheme {
        PartnerPage(listOf(PartnerAction.Company, PartnerAction.Business))
    }
}