package com.smarthealth.recipe.searchrecipe.data.api.service


import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.RandomRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.SearchRecipeResponseDTO
import com.smarthealth.recipe.searchrecipe.data.utils.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(EndPoints.SEARCH_MEAL_API)
    suspend fun getMeals(@Query("s") query: String): Response<SearchRecipeResponseDTO>

    @GET(EndPoints.RANDOM_MEAL_API)
    suspend fun getRandomMeals(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Response<RandomRecipeResponseDTO>
}