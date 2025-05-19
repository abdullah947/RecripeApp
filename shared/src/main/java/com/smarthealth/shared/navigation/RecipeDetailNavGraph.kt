package com.smarthealth.shared.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.smarthealth.shared.presentation.detail.DetailRecipeViewModel
import com.smarthealth.shared.presentation.detail.RecipeDetailScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailScreenNavGraph(onBtnModifyClick: () -> Unit) {
    composable<RecipeDetailScreens.DetailScreen> {
        val context = LocalContext.current
        val viewModel: DetailRecipeViewModel = koinViewModel()
        LaunchedEffect(Unit) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is DetailRecipeViewModel.UiEvent.ShowToast -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        RecipeDetailScreen(
            state = viewModel.state ,
            actionEvent = viewModel::onEvent,
            onBtnModifyClick = {
                viewModel.onBtnModifyClick()
                onBtnModifyClick() }
        )
    }
}
