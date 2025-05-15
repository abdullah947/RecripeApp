package com.smarthealth.shared.navigation

import com.smarthealth.shared.presentation.viewmodels.Route
import kotlinx.serialization.Serializable

sealed class RecipeDetailScreens : Route {

    @Serializable
    data class DetailScreen(
        val id: String,
        val title: String,
        val imageUrl: String,
        var instructions: String,
        var ingredientsList: String
    ) : RecipeDetailScreens()
}