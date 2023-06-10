package com.postliu.openai.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.drake.channel.receiveEvent
import com.postliu.openai.base.Constants
import com.postliu.openai.model.BottomBarDestination
import com.postliu.openai.ui.NavGraphs
import com.postliu.openai.ui.appCurrentDestinationAsState
import com.postliu.openai.ui.destinations.LoginScreenDestination
import com.postliu.openai.ui.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Destination
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val lifecycleOwner = LocalLifecycleOwner.current
    lifecycleOwner.receiveEvent<Boolean>(Constants.LOGIN_AGAIN) {
        if (it) {
            navController.navigate(LoginScreenDestination) {
                launchSingleTop = true
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }, modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        DestinationsNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navGraph = NavGraphs.root,
        )
    }
}

@Destination
@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination
    val isShow =
        currentDestination.route in BottomBarDestination.values().map { it.direction.route }
    AnimatedVisibility(visible = isShow, enter = expandVertically(), exit = shrinkVertically()) {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background
        ) {
            BottomBarDestination.values().forEach { destination ->
                BottomNavigationItem(
                    alwaysShowLabel = true,
                    selected = currentDestination == destination.direction,
                    onClick = {
                        navController.navigate(destination.direction) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        if (currentDestination == destination.direction) {
                            Image(
                                imageVector = destination.selectedIcon,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        } else {
                            Image(
                                imageVector = destination.normalIcon,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = destination.label,
                            fontSize = 10.sp,
                            color = if (currentDestination == destination.direction) MaterialTheme.colors.primary else Color.Black,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    })
            }
        }
    }
}

