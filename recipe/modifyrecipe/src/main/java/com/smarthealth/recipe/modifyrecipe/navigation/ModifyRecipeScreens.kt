package com.smarthealth.recipe.modifyrecipe.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ModifyRecipeScreens {

    @Serializable
    data object ModifyRecipeEntryPoint : ModifyRecipeScreens()

    @Serializable
    data object ModifyRecipe : ModifyRecipeScreens()
}