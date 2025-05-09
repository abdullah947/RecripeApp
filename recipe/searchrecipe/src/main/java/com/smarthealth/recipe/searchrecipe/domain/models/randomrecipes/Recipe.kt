package com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes

data class Recipe(
    val id: String,
    val image: String,
    val title: String,
    val analyzedInstructions: List<AnalyzedInstruction>,
)