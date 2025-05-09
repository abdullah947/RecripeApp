package com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)