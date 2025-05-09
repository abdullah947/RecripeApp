package com.smarthealth.shared.navigation

import com.smarthealth.shared.data.models.GridDish
import com.smarthealth.shared.presentation.viewmodels.Route
import kotlinx.serialization.Serializable

sealed class RecipeDetailScreens : Route {
    @Serializable
    data class DetailScreen(val data: String) : RecipeDetailScreens()

}