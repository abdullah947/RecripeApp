package com.smarthealth.recipe.modifyrecipe.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest
import com.smarthealth.recipe.modifyrecipe.domain.models.GeminiResponse

interface ModifyRecipeRepo {
    suspend fun chatWithAi(query: GeminiRequest, apiKey: String): NetworkResult<GeminiResponse>
}