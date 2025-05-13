package com.smarthealth.recipe.modifyrecipe.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.GeminiResponseDTO
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest

interface ModifyRecipeRepo {
    suspend fun chatWithAi(query: GeminiRequest, apiKey: String): NetworkResult<GeminiResponseDTO>
}