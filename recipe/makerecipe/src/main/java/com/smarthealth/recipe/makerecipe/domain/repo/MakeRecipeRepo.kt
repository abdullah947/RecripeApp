package com.smarthealth.recipe.makerecipe.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.makerecipe.data.api.models.makerecipes.RecipeByIngredientsResponseDTO


interface MakeRecipeRepo {
    suspend fun  getMealsByIngredients(ingredient: String , apiKey:String): NetworkResult<List<RecipeByIngredientsResponseDTO>>
}
