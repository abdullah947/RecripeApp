package com.smarthealth.recipe.searchrecipe.navigation

import com.smarthealth.shared.presentation.viewmodels.Route
import kotlinx.serialization.Serializable

sealed class SearchRecipeScreens : Route {

    @Serializable
    data object AppEntryPoint : SearchRecipeScreens()

    @Serializable
    data object SearchRecipe : SearchRecipeScreens()
}