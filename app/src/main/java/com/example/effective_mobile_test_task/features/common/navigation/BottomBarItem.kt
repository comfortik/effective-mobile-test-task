package com.example.effective_mobile_test_task.features.common.navigation

import androidx.annotation.DrawableRes
import com.example.effective_mobile_test_task.R


sealed class BottomBarItem {

    abstract val title: Int

    open val route: Route = Route.PlugRoute
    @get:DrawableRes
    abstract val icon: Int

    data object AirTicketsItem: BottomBarItem() {
        override val title = R.string.air_tickets
        override val route: Route = Route.MainScreenRoute
        override val icon: Int = R.drawable.ic_airplane
    }

    data object HotelsItem: BottomBarItem(){
        override val title: Int = R.string.hotels
        override val icon: Int =R.drawable.ic_bed
    }
    data object InShortItem: BottomBarItem(){
        override val title: Int = R.string.in_short
        override val icon: Int =R.drawable.ic_geo
    }
    data object SubscriptionsItem: BottomBarItem(){
        override val title: Int = R.string.subscriptions
        override val icon: Int =R.drawable.ic_bell
    }
    data object ProfileScreen: BottomBarItem(){
        override val title: Int = R.string.profile
        override val icon: Int =R.drawable.ic_profile
    }


}

