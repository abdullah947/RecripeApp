package com.smarthealth.recipe.modifyrecipe.data.api.service

import com.smarthealth.recipe.modifyrecipe.data.api.models.response.GeminiResponseDTO
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest
import com.smarthealth.recipe.modifyrecipe.data.utils.EndPoints
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Response

interface ApiService {

    @POST(EndPoints.GEMINI_API)
    suspend fun chatWithAi(
        @Body request: GeminiRequest,
        @Query("key") apiKey: String
    ): Response<GeminiResponseDTO>
}