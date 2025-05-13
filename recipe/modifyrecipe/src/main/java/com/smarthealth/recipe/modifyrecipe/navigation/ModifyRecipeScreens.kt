package com.smarthealth.recipe.modifyrecipe.navigation

import com.smarthealth.shared.presentation.viewmodels.Route
import kotlinx.serialization.Serializable


@Serializable
sealed class ModifyRecipeScreens : Route {

    @Serializable
    data object ModifyRecipeEntryPoint : ModifyRecipeScreens()

    @Serializable
    data object ModifyRecipe : ModifyRecipeScreens()
}