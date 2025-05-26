package com.smarthealth.recipe.searchrecipe.navigation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeScreen
import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeViewModel
import com.smarthealth.shared.navigation.RecipeDetailScreens
import com.smarthealth.shared.presentation.MainViewModel.MainActivityVM
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.searchRecipeNavGraph(
    navController: NavController
) {
    navigation<SearchRecipeScreens.AppEntryPoint>(startDestination = SearchRecipeScreens.SearchRecipe) {
        composable<SearchRecipeScreens.SearchRecipe> {
            val context = LocalContext.current

            val viewModel: SearchRecipeViewModel = koinViewModel()

            val favouriteRecipes = viewModel.recipes.collectAsState(emptyList())

            val keyboardController = LocalSoftwareKeyboardController.current

            LaunchedEffect(Unit) {
                viewModel.navigationEvent.collectLatest { event ->
                    when (event) {
                        is SearchRecipeViewModel.NavigationEvent.ToDetailScreen -> {
                            val dish = event.dish
                            navController.navigate(
                                RecipeDetailScreens.DetailScreen(
                                    id = dish.id,
                                    title = dish.title,
                                    imageUrl = dish.imageUrl,
                                    instructions = dish.instructions,
                                    ingredientsList = dish.ingredientsList
                                )
                            )
                        }
                    }
                }
            }
            LaunchedEffect(Unit) {
                viewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is SearchRecipeViewModel.UiEvent.HideKeyboard -> {
                            keyboardController?.hide()
                        }

                        is SearchRecipeViewModel.UiEvent.ShowToast -> {
                            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            val activity = LocalActivity.current as ComponentActivity
            val mainVM : MainActivityVM = koinViewModel(viewModelStoreOwner = activity)
            val mainVmAction = mainVM ::onEvent

            SearchRecipeScreen(
                state = viewModel.state,
                favouriteRecipes = favouriteRecipes,
                actionEvent = viewModel::onEvent,
                mainVMAction = mainVmAction

            )
        }
    }
}



