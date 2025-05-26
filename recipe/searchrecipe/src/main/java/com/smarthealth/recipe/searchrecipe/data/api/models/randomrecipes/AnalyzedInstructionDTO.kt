package com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes

import com.smarthealth.recipe.searchrecipe.domain.models.randomrecipes.AnalyzedInstruction


data class AnalyzedInstructionDTO(
    val name: String?,
    val steps: List<StepDTO>?
)

fun AnalyzedInstructionDTO.toDomain(): AnalyzedInstruction {
    return AnalyzedInstruction(
        name = name.orEmpty(),
        steps = steps?.map { it.toDomain() } ?: emptyList()
    )
}

