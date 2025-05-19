package com.smarthealth.recipe.makerecipe.data.models

data class IngredientsListItem(
    val text: String,
    val imageRes: Int,
    val isChecked: Boolean = false,
)
