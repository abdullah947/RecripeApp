package com.smarthealth.shared.navigation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.smarthealth.shared.presentation.MainViewModel.MainActivityVM
import com.smarthealth.shared.presentation.detail.DetailRecipeViewModel
import com.smarthealth.shared.presentation.detail.RecipeDetailScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailScreenNavGraph() {
    composable<RecipeDetailScreens.DetailScreen> {
        val context = LocalContext.current
        val viewModel: DetailRecipeViewModel = koinViewModel()
        LaunchedEffect(Unit) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is DetailRecipeViewModel.UiEvent.ShowToast -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val activity = LocalActivity.current as ComponentActivity
        val mainVM : MainActivityVM = koinViewModel(viewModelStoreOwner = activity)
        val mainVmAction = mainVM ::onEvent

        RecipeDetailScreen(
            mainVMAction = mainVmAction,
            state = viewModel.state ,
            actionEvent = viewModel::onEvent
        )
    }
}
