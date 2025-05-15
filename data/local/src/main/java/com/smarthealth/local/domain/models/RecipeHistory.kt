package com.smarthealth.local.domain.models

data class RecipeHistory(
    val id: Int = 0,
    val dishId: String,
    val title: String,
    val imageUrl: String,
    val instructions: String,
    val ingredientsList: String
)
