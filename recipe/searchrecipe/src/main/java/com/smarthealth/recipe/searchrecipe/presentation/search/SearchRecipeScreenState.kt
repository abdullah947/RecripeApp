package com.smarthealth.recipe.searchrecipe.presentation.search

import androidx.compose.ui.text.input.TextFieldValue
import com.smarthealth.shared.data.models.GridDish

data class SearchRecipeScreenState(
    val id: Int = 0,
    val textSearch: TextFieldValue = TextFieldValue(),
    val btnSearchText: String = "Search",
    val btnMakeText: String = "Make Recipe",
    val placeHolderText: String = "Search Recipe",
    val txtNoData: String = "No recipes found",
    val title: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = true,
    val isSuccess: Boolean = true,
    val recipeList: List<GridDish> = emptyList()
)
