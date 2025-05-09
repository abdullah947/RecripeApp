package com.smarthealth.recipe.makerecipe.data.api.models.makerecipes

import com.smarthealth.recipe.makerecipe.domain.models.RecipeByIngredientsResponse

data class RecipeByIngredientsResponseDTO(
    val id: String?,
    val image: String?,
    val imageType: String?,
    val likes: Int?,
    val missedIngredientCount: Int?,
    val title: String?,
    val unusedIngredients: List<Any>?,
    val usedIngredientCount: Int?
)

fun RecipeByIngredientsResponseDTO.toDomain(): RecipeByIngredientsResponse {
    return RecipeByIngredientsResponse(
        id = id.orEmpty(),
        image = image.orEmpty(),
        title = title.orEmpty()
    )
}