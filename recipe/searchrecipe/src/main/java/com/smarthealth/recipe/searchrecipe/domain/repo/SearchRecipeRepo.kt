package com.smarthealth.recipe.searchrecipe.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.RandomRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.SearchRecipeResponseDTO

interface SearchRecipeRepo {
    suspend fun getMeals(query: String): NetworkResult<SearchRecipeResponseDTO>
    suspend fun getRandomMeals(number: Int, apiKey: String): NetworkResult<RandomRecipeResponseDTO>
}
