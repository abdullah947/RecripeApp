package com.smarthealth.recipe.makerecipe.navigation


import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.makerecipe.presentation.make.MakeRecipeScreen
import com.smarthealth.recipe.makerecipe.presentation.make.MakeRecipeViewModel
import com.smarthealth.shared.navigation.RecipeDetailScreens
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.makeRecipeGraph(mainActivitySharedViewModel: MainActivitySharedViewModel) {
    navigation<MakeRecipeScreens.MakeRecipeEntryPoint>(startDestination = MakeRecipeScreens.MakeRecipe) {
        composable<MakeRecipeScreens.MakeRecipe> {
            val viewModel: MakeRecipeViewModel = koinViewModel()
            val keyboardController = LocalSoftwareKeyboardController.current
            MakeRecipeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                onclick = {
                    keyboardController?.hide()
                    viewModel.viewModelScope.launch(Dispatchers.IO)
                    {
                        viewModel.getRecipeData()
                    }
                }, onItemClick = { dish ->
                    val dishJson = Json.encodeToString(dish)
                    mainActivitySharedViewModel.navigate(RecipeDetailScreens.DetailScreen(dishJson))
                }
            )
        }
    }
}
