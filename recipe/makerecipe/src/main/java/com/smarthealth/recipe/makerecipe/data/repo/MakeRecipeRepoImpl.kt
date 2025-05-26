package com.smarthealth.recipe.makerecipe.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.makerecipe.data.api.models.makerecipes.toDomain
import com.smarthealth.recipe.makerecipe.data.api.service.ApiService
import com.smarthealth.recipe.makerecipe.domain.models.RecipeByIngredientsResponse
import com.smarthealth.recipe.makerecipe.domain.repo.MakeRecipeRepo

class MakeRecipeRepoImpl(private val apiService: ApiService) : MakeRecipeRepo {
    override suspend fun getMealsByIngredients(
        ingredient: String,
        apiKey: String,
    ): NetworkResult<List<RecipeByIngredientsResponse>> {

        return safeApiCall(
            apiCall = { apiService.getRecipesByIngredients(ingredient, apiKey) },
            mapper = { (it.map { dto -> dto.toDomain() }) }
        )
    }
}