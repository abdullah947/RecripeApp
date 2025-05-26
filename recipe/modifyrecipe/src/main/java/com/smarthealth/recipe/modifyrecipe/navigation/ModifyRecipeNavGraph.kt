package com.smarthealth.recipe.modifyrecipe.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.modifyrecipe.presentation.modify.ModifyRecipeScreen
import com.smarthealth.recipe.modifyrecipe.presentation.modify.ModifyRecipeViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.modifyNavRecipeGraph() {
    navigation<ModifyRecipeScreens.ModifyRecipeEntryPoint>(startDestination = ModifyRecipeScreens.ModifyRecipe) {
        composable<ModifyRecipeScreens.ModifyRecipe> {
            val context = LocalContext.current
            val viewModel: ModifyRecipeViewModel = koinViewModel()
            LaunchedEffect(Unit) {
                viewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is ModifyRecipeViewModel.UiEvent.ShowToast -> {
                            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            ModifyRecipeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent
            )
        }
    }
}