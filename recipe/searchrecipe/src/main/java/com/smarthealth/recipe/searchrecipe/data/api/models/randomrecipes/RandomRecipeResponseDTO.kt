package com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes

import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.RandomRecipeResponse

data class RandomRecipeResponseDTO(
    val recipes: List<RecipeDTO>?
)

fun RandomRecipeResponseDTO.toDomain(): RandomRecipeResponse {
    return RandomRecipeResponse(
        recipes = recipes?.map { it.toDomain() } ?: emptyList()
    )
}

