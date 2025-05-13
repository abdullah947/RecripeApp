package com.smarthealth.recipe.modifyrecipe.data.api.models.response

import com.smarthealth.recipe.modifyrecipe.domain.models.Candidate
import com.smarthealth.recipe.modifyrecipe.domain.models.Content

data class CandidateDTO(
    val avgLogprobs: Double?,
    val content: ContentDTO?,
    val finishReason: String?
)

fun CandidateDTO.toDomain(): Candidate {
    return Candidate(
        content = content?.toDomain() ?: Content(parts = emptyList(),role = "")
    )
}