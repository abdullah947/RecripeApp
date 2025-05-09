package com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes

import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.Ingredient

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