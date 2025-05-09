package com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes

import com.smarthealth.recipe.searchrecipe.domain.models.searchrecipe.SearchRecipeResponse

data class SearchRecipeResponseDTO(
    val meals: List<MealDTO>?
)

fun SearchRecipeResponseDTO.toDomain(): SearchRecipeResponse {
    return SearchRecipeResponse(
        meals = meals?.map { it.toDomain() } ?: emptyList()
    )
}