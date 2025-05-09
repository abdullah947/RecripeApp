package com.smarthealth.shared.data.api.models.recipebyid

import com.smarthealth.shared.domain.models.recipebyid.Ingredient

data class IngredientDTO(
    val id: Int?,
    val image: String?,
    val localizedName: String?,
    val name: String?
)

fun IngredientDTO.toDomain(): Ingredient {
    return Ingredient(
        name = this.name.orEmpty()
    )
}