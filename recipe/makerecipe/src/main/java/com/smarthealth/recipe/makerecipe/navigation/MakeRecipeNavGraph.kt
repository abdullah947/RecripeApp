package com.smarthealth.recipe.makerecipe.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.makerecipe.presentation.make.MakeRecipeScreen
import com.smarthealth.recipe.makerecipe.presentation.make.MakeRecipeViewModel
import com.smarthealth.shared.navigation.RecipeDetailScreens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.makeRecipeGraph(navController: NavController) {
    navigation<MakeRecipeScreens.MakeRecipeEntryPoint>(startDestination = MakeRecipeScreens.MakeRecipe) {
        composable<MakeRecipeScreens.MakeRecipe> {
            val context = LocalContext.current
            val viewModel: MakeRecipeViewModel = koinViewModel()
            val keyboardController = LocalSoftwareKeyboardController.current

            LaunchedEffect(Unit) {
                viewModel.navigationEvent.collect { event ->
                    when (event) {
                        is MakeRecipeViewModel.NavigationEvent.ToDetailScreen -> {
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
                        is MakeRecipeViewModel.UiEvent.HideKeyboard -> {
                            keyboardController?.hide()
                        }
                        is MakeRecipeViewModel.UiEvent.ShowToast -> {
                            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            MakeRecipeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent
            )
        }
    }
}
