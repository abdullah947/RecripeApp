package com.smarthealth.recipe.searchrecipe.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.toDomain
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

        return safeApiCall(
            apiCall = { mealApiService.getMeals(query) },
            mapper = { it.toDomain() }
        )
    }

    override suspend fun getRandomMeals(
        number: Int,
        apiKey: String,
    ): NetworkResult<RandomRecipeResponse> {

        return safeApiCall(
            apiCall = { spoonApiService.getRandomMeals(number, apiKey) },
            mapper = { it.toDomain() }
        )
    }

}