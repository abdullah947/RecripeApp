package com.smarthealth.recipe.modifyrecipe.presentation.modify

import androidx.compose.ui.text.input.TextFieldValue
import com.smarthealth.recipe.modifyrecipe.domain.models.Content

data class ModifyRecipeScreenState(
    val id: Int = 0,
    val textRequest: TextFieldValue = TextFieldValue(),
    val placeHolderText: String = "Ask Any Thing",
    val isLoading: Boolean = false,
    var isSuccess: Boolean = true,
    val msgList: List<Content> = emptyList()
)
