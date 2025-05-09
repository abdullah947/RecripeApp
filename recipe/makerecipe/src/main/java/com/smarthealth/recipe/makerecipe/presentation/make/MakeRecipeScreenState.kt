package com.smarthealth.recipe.makerecipe.presentation.make

import androidx.compose.ui.text.input.TextFieldValue
import com.smarthealth.recipe.makerecipe.data.models.IngredientsListItem
import com.smarthealth.shared.data.models.GridDish

data class MakeRecipeScreenState(
    val id: Int = 0,
    val textSearch: TextFieldValue = TextFieldValue(),
    val btnText: String = "Search",
    val placeHolderText: String = "Add Ingredients",
    val title: String = "",
    val imageUrl: String = "",
    val isMakeBtnClicked: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = true,
    val recipeList: List<GridDish> = emptyList(),
    val ingredientsList: List<IngredientsListItem> = emptyList()

)
