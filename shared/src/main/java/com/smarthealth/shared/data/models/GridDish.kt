package com.smarthealth.shared.data.models

import kotlinx.serialization.Serializable


@Serializable
data class GridDish(
    val id: String,
    val title: String,
    val imageUrl: String,
    val instructions: String,
    val ingredientsList: String
)