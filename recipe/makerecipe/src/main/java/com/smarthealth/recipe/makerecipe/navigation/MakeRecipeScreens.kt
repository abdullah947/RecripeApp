package com.smarthealth.recipe.makerecipe.navigation

import com.smarthealth.shared.presentation.viewmodels.Route
import kotlinx.serialization.Serializable

sealed class MakeRecipeScreens: Route {

    @Serializable
    data object MakeRecipeEntryPoint : MakeRecipeScreens()

    @Serializable
    data object MakeRecipe : MakeRecipeScreens()

}