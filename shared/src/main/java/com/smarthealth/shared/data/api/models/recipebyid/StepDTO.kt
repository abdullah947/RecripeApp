package com.smarthealth.shared.data.api.models.recipebyid

import com.smarthealth.shared.domain.models.recipebyid.Step

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
