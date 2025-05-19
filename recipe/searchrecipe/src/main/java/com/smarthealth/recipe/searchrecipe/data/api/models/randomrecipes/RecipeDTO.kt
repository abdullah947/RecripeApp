package com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes

import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.Recipe

data class RecipeDTO(
    val aggregateLikes: Int?,
    val analyzedInstructions: List<AnalyzedInstructionDTO>?,
    val cheap: Boolean?,
    val cookingMinutes: Any?,
    val creditsText: String?,
    val cuisines: List<String?>,
    val dairyFree: Boolean?,
    val diets: List<String>?,
    val dishTypes: List<String>,
    val gaps: String?,
    val glutenFree: Boolean?,
    val healthScore: Double?,
    val id: Int?,
    val image: String?,
    val imageType: String?,
    val instructions: String?,
    val license: String?,
    val lowFodmap: Boolean?,
    val occasions: List<String>?,
    val originalId: Any?,
    val preparationMinutes: Any?,
    val pricePerServing: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularScore: Double?,
    val spoonacularSourceUrl: String?,
    val summary: String?,
    val sustainable: Boolean?,
    val title: String?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val weightWatcherSmartPoints: Int?
)

fun RecipeDTO.toDomain(): Recipe {
    return Recipe(
        id = id.toString(),
        image = image.orEmpty(),
        title = title.orEmpty(),
        analyzedInstructions = analyzedInstructions?.map { it.toDomain() } ?: emptyList()
    )
}