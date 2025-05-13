package com.smartHealth.recipefinder.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.smarthealth.recipe.makerecipe.navigation.makeRecipeGraph
import com.smarthealth.recipe.modifyrecipe.navigation.ModifyRecipeScreens
import com.smarthealth.recipe.modifyrecipe.navigation.modifyNavRecipeGraph
import com.smarthealth.recipe.searchrecipe.navigation.SearchRecipeScreens
import com.smarthealth.recipe.searchrecipe.navigation.searchRecipeNavGraph
import com.smarthealth.shared.navigation.detailScreenNavGraph
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    mainActivitySharedViewModel: MainActivitySharedViewModel,
    startDestination: Any = SearchRecipeScreens.AppEntryPoint
) {
    NavHost(
        navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
    ) {
        searchRecipeNavGraph(mainActivitySharedViewModel)
        makeRecipeGraph(mainActivitySharedViewModel)
        detailScreenNavGraph(onclick = {mainActivitySharedViewModel.navigate(ModifyRecipeScreens.ModifyRecipeEntryPoint)})
        modifyNavRecipeGraph()
    }
}