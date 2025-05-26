package com.smartHealth.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smartHealth.recipefinder.navigation.AppNavGraph
import com.smartHealth.recipefinder.ui.theme.RecipeFinderTheme
import com.smarthealth.recipe.makerecipe.navigation.MakeRecipeScreens
import com.smarthealth.recipe.modifyrecipe.navigation.ModifyRecipeScreens
import com.smarthealth.shared.presentation.MainViewModel.MainActivityVM
import com.smarthealth.shared.presentation.MainViewModel.MainVmNavigationEvent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    private lateinit var mainActivityVM: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val activity = LocalActivity.current as ComponentActivity
            mainActivityVM = koinViewModel(viewModelStoreOwner = activity)


            val navController: NavHostController = rememberNavController()
            RecipeFinderTheme {
                KoinContext {

                    AppNavGraph(navController)
                }
            }



            LaunchedEffect(Unit) {
                mainActivityVM.maiNavEvents.collectLatest {
                    when(it){
                        MainVmNavigationEvent.MakeRecipe.Feature -> {
                            navController.navigate(MakeRecipeScreens.MakeRecipeEntryPoint)
                        }

                        MainVmNavigationEvent.ModifyRecipe.Feature -> {
                            navController.navigate(ModifyRecipeScreens.ModifyRecipeEntryPoint)
                        }
                    }
                }
            }
        }
    }
}

