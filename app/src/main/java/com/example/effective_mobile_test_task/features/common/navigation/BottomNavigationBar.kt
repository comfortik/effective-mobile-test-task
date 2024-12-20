package com.example.effective_mobile_test_task.features.common.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.features.allTicketsScreen.AllTicketsScreen
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.AirTicketsItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.HotelsItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.InShortItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.ProfileScreen
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem.SubscriptionsItem
import com.example.effective_mobile_test_task.features.common.screens.PlugScreen
import com.example.effective_mobile_test_task.features.main.AirTicketsScreen
import com.example.effective_mobile_test_task.features.searchScreen.SearchScreen
import com.example.effective_mobile_test_task.features.ticketsScreen.TicketsScreen

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
fun CreateNavigation(navController: NavHostController,
                     startDestination: Route,
                     modifier: Modifier,
                     onRouteChanged: (Route)->Unit
    ){
    NavHost(navController =  navController, startDestination = startDestination, modifier = modifier){
        composable<Route.AirTicketsRoute>{
            AirTicketsScreen{
                navController.navigate(Route.SearchRoute)
            }
            onRouteChanged(Route.AirTicketsRoute)
        }
        composable<Route.PlugRoute> { PlugScreen(); onRouteChanged(Route.PlugRoute) }
        composable<Route.SearchRoute> {
            SearchScreen(
                onPlugClick = { navController.navigate(Route.PlugRoute)},
                onSearchClick = { departure, arrived->
                    navController.navigate(Route.AllTicketsRoute(departure, arrived))
                }
            )
            onRouteChanged(Route.SearchRoute)
        }
        composable<Route.TicketsRoute> {navBackStackEntry->
            val arrivedCity = navBackStackEntry.toRoute<Route.TicketsRoute>().arrivedCity
            val departureCity = navBackStackEntry.toRoute<Route.TicketsRoute>().departureCity
            val date = navBackStackEntry.toRoute<Route.TicketsRoute>().date
            TicketsScreen(departureCity, arrivedCity, date){
                navController.navigateUp()
            }

        }
        composable<Route.AllTicketsRoute> {navBackStackEntry->
            val arrivedCity = navBackStackEntry.toRoute<Route.AllTicketsRoute>().arrivedCity
            val departureCity = navBackStackEntry.toRoute<Route.AllTicketsRoute>().departureCity
            AllTicketsScreen(departureCity, arrivedCity,
                onButtonClicked = { departureCityClick, arrivedCityClick, date->
                    navController.navigate(Route.TicketsRoute(departureCityClick, arrivedCityClick, date))
                },
                onBackPressed = {navController.navigateUp()}
            )
            onRouteChanged(Route.AllTicketsRoute(departureCity, arrivedCity))
        }
    }
}


