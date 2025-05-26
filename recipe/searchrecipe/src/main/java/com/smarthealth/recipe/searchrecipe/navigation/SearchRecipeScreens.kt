package com.smarthealth.recipe.searchrecipe.navigation


import kotlinx.serialization.Serializable

sealed class SearchRecipeScreens {
    @Serializable
    data object AppEntryPoint : SearchRecipeScreens()

    @Serializable
    data object SearchRecipe : SearchRecipeScreens()
}