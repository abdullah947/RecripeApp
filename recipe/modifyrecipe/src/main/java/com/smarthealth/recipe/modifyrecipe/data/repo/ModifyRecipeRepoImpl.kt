package com.smarthealth.recipe.modifyrecipe.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.GeminiResponseDTO
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.toDomain
import com.smarthealth.recipe.modifyrecipe.data.api.service.ApiService
import com.smarthealth.recipe.modifyrecipe.domain.models.GeminiResponse
import com.smarthealth.recipe.modifyrecipe.domain.repo.ModifyRecipeRepo

class ModifyRecipeRepoImpl(private val apiService: ApiService): ModifyRecipeRepo {
    override suspend fun chatWithAi(
        query: GeminiRequest,
        apiKey: String
    ): NetworkResult<GeminiResponse> {
        val result: NetworkResult<GeminiResponseDTO> = safeApiCall {
            apiService.chatWithAi(query,apiKey)
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