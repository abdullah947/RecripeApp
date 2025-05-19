package com.smarthealth.shared.data.api.service

import com.smarthealth.shared.data.api.models.recipebyid.DetailRecipeResponseDTO
import com.smarthealth.shared.data.utils.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(EndPoints.RECIPE_DETAIL_API)
    suspend fun getAnalyzedInstructions(
        @Path("id") recipeId: String,
        @Query("apiKey") apiKey: String
    ): Response<List<DetailRecipeResponseDTO>>
}
