package com.smarthealth.recipe.modifyrecipe.data.api.models.response

import com.smarthealth.recipe.modifyrecipe.domain.models.GeminiResponse

data class GeminiResponseDTO(
    val candidates: List<CandidateDTO>?,
    val modelVersion: String?,
)

fun GeminiResponseDTO.toDomain(): GeminiResponse {
    return GeminiResponse(
        candidates = candidates?.map { it.toDomain()}?: emptyList(),
    )
}