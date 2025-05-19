package com.smarthealth.recipe.makerecipe.navigation

import kotlinx.serialization.Serializable

sealed class MakeRecipeScreens {

    @Serializable
    data object MakeRecipeEntryPoint : MakeRecipeScreens()

    @Serializable
    data object MakeRecipe : MakeRecipeScreens()

}