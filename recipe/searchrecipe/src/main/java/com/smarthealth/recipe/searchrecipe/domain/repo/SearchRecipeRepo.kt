package com.smarthealth.recipe.searchrecipe.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.RandomRecipeResponse
import com.smarthealth.recipe.searchrecipe.domain.models.searchrecipe.SearchRecipeResponse

interface SearchRecipeRepo {
    suspend fun getMeals(query: String): NetworkResult<SearchRecipeResponse>
    suspend fun getRandomMeals(number: Int, apiKey: String): NetworkResult<RandomRecipeResponse>
}
