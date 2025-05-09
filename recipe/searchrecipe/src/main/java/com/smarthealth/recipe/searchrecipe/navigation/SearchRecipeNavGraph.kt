package com.smarthealth.recipe.searchrecipe.navigation


import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.makerecipe.navigation.MakeRecipeScreens
import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeScreen
import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeViewModel
import com.smarthealth.shared.navigation.RecipeDetailScreens
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.searchRecipeNavGraph(
    mainActivitySharedViewModel: MainActivitySharedViewModel
) {
    navigation<SearchRecipeScreens.AppEntryPoint>(startDestination = SearchRecipeScreens.SearchRecipe) {
        composable<SearchRecipeScreens.SearchRecipe> {
            val viewModel: SearchRecipeViewModel = koinViewModel()
            val keyboardController = LocalSoftwareKeyboardController.current
            SearchRecipeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                onSearchClick = {
                    keyboardController?.hide()
                    viewModel.getSearchRecipeData()
                }, onItemClick = { dish ->
                    val dishJson = Json.encodeToString(dish)
                    mainActivitySharedViewModel.navigate(RecipeDetailScreens.DetailScreen(dishJson))
                }, onMakeClick = {
                    mainActivitySharedViewModel.navigate(MakeRecipeScreens.MakeRecipeEntryPoint)
                }
            )

        }
    }
}



