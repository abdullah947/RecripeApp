package com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)