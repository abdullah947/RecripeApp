package com.smartHealth.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smartHealth.recipefinder.navigation.AppNavGraph
import com.smartHealth.recipefinder.ui.theme.RecipeFinderTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeFinderTheme {
                KoinContext {
                    val navController: NavHostController = rememberNavController()
                    AppNavGraph(navController)
                }
            }
        }
    }
}

