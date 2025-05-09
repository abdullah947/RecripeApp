package com.smarthealth.shared.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.smarthealth.shared.presentation.detail.DetailRecipeViewModel
import com.smarthealth.shared.presentation.detail.RecipeDetailScreen
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailScreenNavGraph(mainActivitySharedViewModel: MainActivitySharedViewModel) {
    composable<RecipeDetailScreens.DetailScreen> {
        val viewModel: DetailRecipeViewModel = koinViewModel()
        RecipeDetailScreen(state = viewModel.state, onclick = {})
    }
}