package com.smarthealth.shared.navigation


import kotlinx.serialization.Serializable

sealed class RecipeDetailScreens  {

    @Serializable
    data class DetailScreen(
        val id: String,
        val title: String,
        val imageUrl: String,
        var instructions: String,
        var ingredientsList: String
    ) : RecipeDetailScreens()
}