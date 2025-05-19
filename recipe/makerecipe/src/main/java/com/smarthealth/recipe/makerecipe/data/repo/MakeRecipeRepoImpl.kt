package com.smarthealth.recipe.makerecipe.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.makerecipe.data.api.models.makerecipes.RecipeByIngredientsResponseDTO
import com.smarthealth.recipe.makerecipe.data.api.models.makerecipes.toDomain
import com.smarthealth.recipe.makerecipe.data.api.service.ApiService
import com.smarthealth.recipe.makerecipe.domain.models.RecipeByIngredientsResponse
import com.smarthealth.recipe.makerecipe.domain.repo.MakeRecipeRepo

class MakeRecipeRepoImpl(private val apiService: ApiService) : MakeRecipeRepo {
    override suspend fun getMealsByIngredients(
        ingredient: String,
        apiKey: String
    ): NetworkResult<List<RecipeByIngredientsResponse>> {

        val result: NetworkResult<List<RecipeByIngredientsResponseDTO>> = safeApiCall {
            apiService.getRecipesByIngredients(ingredient, apiKey)
        }
        return when (result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    NetworkResult.Success(it.map { dto -> dto.toDomain() })
                } ?: NetworkResult.Error("Empty response")
            }
            is NetworkResult.Error -> NetworkResult.Error(result.message ?: "Unknown error")
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }
}