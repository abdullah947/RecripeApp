package com.smarthealth.recipe.searchrecipe.navigation

import android.widget.Toast
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
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.searchRecipeNavGraph(
    navController: NavController, onBtnMakeClick: () -> Unit,
) {
    navigation<SearchRecipeScreens.AppEntryPoint>(startDestination = SearchRecipeScreens.SearchRecipe) {
        composable<SearchRecipeScreens.SearchRecipe> {
            val context = LocalContext.current
            val viewModel: SearchRecipeViewModel = koinViewModel()

            val favouriteRecipes = viewModel.recipes.collectAsState(emptyList())

            val keyboardController = LocalSoftwareKeyboardController.current

            LaunchedEffect(Unit) {
                viewModel.navigationEvent.collect { event ->
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
                viewModel.uiEvent.collect { event ->
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
            SearchRecipeScreen(
                state = viewModel.state,
                favouriteRecipes = favouriteRecipes,
                actionEvent = viewModel::onEvent,
                onBtnMakeClick = onBtnMakeClick
            )

        }
    }
}



