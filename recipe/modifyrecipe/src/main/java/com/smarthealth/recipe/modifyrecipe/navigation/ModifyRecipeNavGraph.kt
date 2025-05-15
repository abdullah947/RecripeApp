package com.smarthealth.recipe.modifyrecipe.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.smarthealth.recipe.modifyrecipe.presentation.modify.ModifyRecipeScreen
import com.smarthealth.recipe.modifyrecipe.presentation.modify.ModifyRecipeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.modifyNavRecipeGraph() {
    navigation<ModifyRecipeScreens.ModifyRecipeEntryPoint>(startDestination = ModifyRecipeScreens.ModifyRecipe) {
        composable<ModifyRecipeScreens.ModifyRecipe> {
            val viewModel: ModifyRecipeViewModel = koinViewModel()

            ModifyRecipeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                onSendClick = {
                    viewModel.chatWithAi()
                }
            )

        }
    }
}