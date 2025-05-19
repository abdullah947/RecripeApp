package com.smarthealth.recipe.searchrecipe.presentation.search

import androidx.compose.ui.text.input.TextFieldValue
import com.smarthealth.shared.data.models.GridDish

data class SearchRecipeScreenState(
    val id: String = "",
    val textSearch: TextFieldValue = TextFieldValue(),
    val title: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = true,
    val isSuccess: Boolean = true,
    val recipeList: List<GridDish> = emptyList(),
    val favouriteList: List<GridDish> = emptyList(),
    val searchSuggestions: List<String> = emptyList()
)
