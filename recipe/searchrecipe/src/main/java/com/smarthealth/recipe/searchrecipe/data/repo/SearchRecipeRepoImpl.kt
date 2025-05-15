package com.smarthealth.recipe.searchrecipe.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.RandomRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.SearchRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.api.service.ApiService
import com.smarthealth.recipe.searchrecipe.domain.repo.SearchRecipeRepo
import javax.inject.Named

class SearchRecipeRepoImpl(
    @Named("MealApi") private val mealApiService: ApiService,
    @Named("SpoonApi") private val spoonApiService: ApiService
) : SearchRecipeRepo {
    override suspend fun getMeals(query: String): NetworkResult<SearchRecipeResponseDTO> {
        return safeApiCall {
            mealApiService.getMeals(query)
        }
    }
    override suspend fun getRandomMeals(
        number: Int,
        apiKey: String
    ): NetworkResult<RandomRecipeResponseDTO> {
        return safeApiCall {
            spoonApiService.getRandomMeals(number, apiKey)
        }

    }

}