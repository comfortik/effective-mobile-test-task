package com.example.effective_mobile_test_task.features.common.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.features.allTicketsScreen.AllTicketsScreen
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.AirTicketsItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.HotelsItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.InShortItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.ProfileScreen
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.SubscriptionsItem
import com.example.effective_mobile_test_task.features.common.screens.PlugScreen
import com.example.effective_mobile_test_task.features.main.MainScreen
import com.example.effective_mobile_test_task.features.searchScreen.SearchScreen

@Composable
fun BottomNavigationBar(
    onItemClick: (BottomBarItem)->Unit,
    selectedBottomBarItem: BottomBarItem
) {
    NavigationBar (
        containerColor = Color.Black,
        contentColor = Color.White,
        modifier = Modifier.padding(horizontal = 16.dp)
    ){
        val bottomBarItems = listOf(
        AirTicketsItem,
        HotelsItem,
        InShortItem,
        SubscriptionsItem,
        ProfileScreen
    )
        bottomBarItems.forEach{ item->
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = selectedBottomBarItem==item,
                onClick = { onItemClick(item)},
                label = {
                    Text(text= stringResource(item.title), style = TextStyle(fontSize = 10.sp))
                },
                icon = { Icon(painterResource(item.icon), contentDescription = null, ) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(R.color.blue),
                    selectedTextColor = colorResource(R.color.blue),
                    unselectedIconColor = colorResource(R.color.light_gray),
                    indicatorColor = Color.Transparent
                ),
                alwaysShowLabel = true,
            )
        }
    }
}




@Composable
fun CreateNavigation(navController: NavHostController, startDestination: Route,
                     onRouteChanged: (Route)->Unit, modifier: Modifier){
    NavHost(navController =  navController, startDestination = startDestination, modifier = modifier){
        composable<Route.MainScreenRoute>{ MainScreen(); onRouteChanged(Route.MainScreenRoute) }
        composable<Route.PlugRoute> { PlugScreen(); onRouteChanged(Route.PlugRoute) }
        composable<Route.SearchRoute> { SearchScreen(); onRouteChanged(Route.SearchRoute) }
        composable<Route.AllTicketsRoute> { AllTicketsScreen(); onRouteChanged(Route.AllTicketsRoute) }
    }
}

