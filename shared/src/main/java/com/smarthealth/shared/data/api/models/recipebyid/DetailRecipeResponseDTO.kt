package com.smarthealth.shared.data.api.models.recipebyid

import com.smarthealth.shared.domain.models.recipebyid.DetailRecipeResponse

data class DetailRecipeResponseDTO(
    val name: String?,
    val steps: List<StepDTO>?
)

fun DetailRecipeResponseDTO.toDomain(): DetailRecipeResponse {
    return DetailRecipeResponse(
        steps = steps?.map { it.toDomain() } ?: emptyList()
    )
}