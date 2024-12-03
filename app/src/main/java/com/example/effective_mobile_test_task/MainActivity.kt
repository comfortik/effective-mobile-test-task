package com.example.effective_mobile_test_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem
import com.example.effective_mobile_test_task.features.common.navigation.BottomNavigationBar
import com.example.effective_mobile_test_task.features.common.navigation.CreateNavigation
import com.example.effective_mobile_test_task.features.common.navigation.Route
import com.example.effective_mobile_test_task.features.main.models.MainIntent
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
                        BottomNavigationBar(
                            onItemClick = {bottomBarItem ->
                                viewModel.handleIntent(MainIntent.OnBottomItemClick(bottomBarItem))
                                navHostController.navigate(bottomBarItem.route)
                            },
                            selectedBottomBarItem = state.value.currentNavBarItem
                        )
                    }
                    ) { innerPadding ->
                    CreateNavigation(navController = navHostController,
                        startDestination = Route.MainScreenRoute,
                        onRouteChanged = { route->
                            viewModel.handleIntent(MainIntent.OnRouteChanged(route))
                        },
                        modifier = Modifier.padding(innerPadding),

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EffectivemobiletesttaskTheme {
        Greeting("Android")
    }
}