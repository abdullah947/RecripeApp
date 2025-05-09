package com.smarthealth.shared.presentation.detail

data class DetailRecipeScreenState(
    val title: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = true,
    val instructions: String = "",
    val ingredients: String = "",
    val btnModifyText: String = "Modify Recipe"
)
