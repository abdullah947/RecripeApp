package com.smarthealth.recipe.modifyrecipe.data.api.models.response

import com.smarthealth.recipe.modifyrecipe.domain.models.Part

data class PartDTO(
    val text: String?
)

fun PartDTO.toDomain(): Part {

    return Part(
        text = text.orEmpty()
    )
}
