package com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes

import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.toDomain
import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.Ingredient
import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.Step

data class StepDTO(
    val ingredients: List<IngredientDTO>?,
    val number: Int?,
    val step: String?
)

fun StepDTO.toDomain(): Step {
    return Step(
        ingredients = ingredients?.map { it.toDomain() } ?: emptyList(),
        number = number ?: 0,
        step = step.orEmpty()
    )
}


