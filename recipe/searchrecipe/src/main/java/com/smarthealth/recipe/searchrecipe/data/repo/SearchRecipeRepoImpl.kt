package com.smarthealth.recipe.searchrecipe.data.repo

import com.smarthealth.network.utils.NetworkResult

import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.RandomRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.toDomain
import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.SearchRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.toDomain
import com.smarthealth.recipe.searchrecipe.data.api.service.ApiService
import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.RandomRecipeResponse
import com.smarthealth.recipe.searchrecipe.domain.models.searchrecipe.SearchRecipeResponse
import com.smarthealth.recipe.searchrecipe.domain.repo.SearchRecipeRepo
import javax.inject.Named

class SearchRecipeRepoImpl(
    @Named("MealApi") private val mealApiService: ApiService,
    @Named("SpoonApi") private val spoonApiService: ApiService,
) : SearchRecipeRepo {
    override suspend fun getMeals(query: String): NetworkResult<SearchRecipeResponse> {
        val result: NetworkResult<SearchRecipeResponseDTO> = safeApiCall {
            mealApiService.getMeals(query)
        }
        return when (result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    NetworkResult.Success(it.toDomain())
                } ?: NetworkResult.Error("Empty response")
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(result.message ?: "Unknown error")
            }

            is NetworkResult.Loading -> {
                NetworkResult.Loading()
            }
        }
    }

    override suspend fun getRandomMeals(
        number: Int,
        apiKey: String,
    ): NetworkResult<RandomRecipeResponse> {

        val result: NetworkResult<RandomRecipeResponseDTO> = safeApiCall {
            spoonApiService.getRandomMeals(number, apiKey)
        }
        return when (result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    NetworkResult.Success(it.toDomain())
                } ?: NetworkResult.Error("Empty response")
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(result.message ?: "Unknown error")
            }

            is NetworkResult.Loading -> {
                NetworkResult.Loading()
            }
        }
    }
}