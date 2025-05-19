package com.smarthealth.recipe.makerecipe.data.api.service

import com.smarthealth.recipe.makerecipe.data.api.models.makerecipes.RecipeByIngredientsResponseDTO
import com.smarthealth.recipe.makerecipe.data.utils.EndPoints

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(EndPoints.MAKE_RECIPE_API)
    suspend fun getRecipesByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("apiKey") apiKey: String,
    ): Response<List<RecipeByIngredientsResponseDTO>>
}