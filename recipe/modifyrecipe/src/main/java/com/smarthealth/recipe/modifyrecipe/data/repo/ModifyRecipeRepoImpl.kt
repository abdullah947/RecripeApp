package com.smarthealth.recipe.modifyrecipe.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.toDomain
import com.smarthealth.recipe.modifyrecipe.data.api.service.ApiService
import com.smarthealth.recipe.modifyrecipe.domain.models.GeminiResponse
import com.smarthealth.recipe.modifyrecipe.domain.repo.ModifyRecipeRepo

class ModifyRecipeRepoImpl(private val apiService: ApiService) : ModifyRecipeRepo {
    override suspend fun chatWithAi(
        query: GeminiRequest,
        apiKey: String,
    ): NetworkResult<GeminiResponse> {

        return safeApiCall(
            apiCall = { apiService.chatWithAi(query, apiKey) },
            mapper = { it.toDomain() }
        )
    }
}