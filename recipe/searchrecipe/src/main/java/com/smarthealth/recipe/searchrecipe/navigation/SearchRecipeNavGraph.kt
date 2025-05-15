package com.smarthealth.recipe.searchrecipe.navigation


import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.makerecipe.navigation.MakeRecipeScreens
import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeScreen
import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeViewModel
import com.smarthealth.shared.navigation.RecipeDetailScreens
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.searchRecipeNavGraph(
    mainActivitySharedViewModel: MainActivitySharedViewModel
) {
    navigation<SearchRecipeScreens.AppEntryPoint>(startDestination = SearchRecipeScreens.SearchRecipe) {
        composable<SearchRecipeScreens.SearchRecipe> {
            val viewModel: SearchRecipeViewModel = koinViewModel()

            val favouriteRecipes = viewModel.recipes.collectAsState(emptyList())

            val keyboardController = LocalSoftwareKeyboardController.current
            SearchRecipeScreen(
                state = viewModel.state,
                favouriteRecipes = favouriteRecipes,
                actionEvent = viewModel::onEvent,
                onSearchClick = {
                    keyboardController?.hide()
                    viewModel.getSearchRecipeData()
                }, onItemClick = { dish ->
//                    val dishJson = Json.encodeToString(dish)
                    mainActivitySharedViewModel.navigate(
                        RecipeDetailScreens.DetailScreen(
                            id = dish.id,
                            title = dish.title,
                            imageUrl = dish.imageUrl,
                            instructions = dish.instructions,
                            ingredientsList = dish.ingredientsList
                        )
                    )
                }, onMakeClick = {
                    mainActivitySharedViewModel.navigate(MakeRecipeScreens.MakeRecipeEntryPoint)
                }
            )

        }
    }
}



