package com.smarthealth.shared.domain.models.recipebyid

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)