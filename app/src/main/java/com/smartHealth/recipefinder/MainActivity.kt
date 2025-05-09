package com.smartHealth.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smartHealth.recipefinder.navigation.AppNavGraph
import com.smartHealth.recipefinder.ui.theme.RecipeFinderTheme
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext


class MainActivity : ComponentActivity() {


    private val mainActivitySharedViewModel by viewModels<MainActivitySharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeFinderTheme {
                KoinContext {
                    val navController: NavHostController = rememberNavController()
                    LaunchedEffect(Unit) {
                        mainActivitySharedViewModel.event.collectLatest { event ->
                            when (event) {
                                is MainActivitySharedViewModel.Event.NavigateToScreen -> {
                                    navController.navigate(event.screen)
                                }
                            }
                        }
                    }
                    AppNavGraph(navController, mainActivitySharedViewModel)
                }
            }
        }
    }
}

