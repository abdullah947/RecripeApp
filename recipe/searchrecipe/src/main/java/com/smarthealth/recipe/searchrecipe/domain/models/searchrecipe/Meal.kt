package com.smarthealth.recipe.searchrecipe.domain.models.searchrecipe

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val ingredientsList: String
)
