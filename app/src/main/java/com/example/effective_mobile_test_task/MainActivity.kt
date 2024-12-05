package com.example.effective_mobile_test_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.effective_mobile_test_task.features.common.navigation.BottomNavigationBar
import com.example.effective_mobile_test_task.features.common.navigation.CreateNavigation
import com.example.effective_mobile_test_task.features.common.navigation.Route
import com.example.effective_mobile_test_task.features.models.MainIntent
import com.example.effective_mobile_test_task.ui.theme.EffectivemobiletesttaskTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel  = koinViewModel()
            val navHostController = rememberNavController()
            val state =  viewModel.screenState.collectAsState()

            EffectivemobiletesttaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar ={
                        Column{
                            if(
                                state.value.currentRoute is Route.AirTicketsRoute
                                || state.value.currentRoute is Route.PlugRoute
                                || state.value.currentRoute is Route.AllTicketsRoute
                                ){
                                Box(Modifier.fillMaxWidth().height(1.dp).background(colorResource(R.color.dark_gray)))
                                BottomNavigationBar(
                                    onItemClick = {bottomBarItem ->
                                        viewModel.handleIntent(MainIntent.OnBottomItemClick(bottomBarItem))
                                        navHostController.navigate(bottomBarItem.route)
                                    },
                                    selectedBottomBarItem = state.value.currentNavBarItem
                                )
                            }
                        }

                    }
                    ) { innerPadding ->
                    CreateNavigation(navController = navHostController,
                        startDestination = Route.AirTicketsRoute,
                        modifier = Modifier.padding(innerPadding),
                        onRouteChanged = {viewModel.handleIntent(MainIntent.OnRouteChanged(it))}

                    )
                }
            }
        }
    }
}

