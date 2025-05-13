package com.smarthealth.recipe.modifyrecipe.data.api.models.response

import com.smarthealth.recipe.modifyrecipe.domain.models.Content

data class ContentDTO(
    val parts: List<PartDTO>?,
    val role: String?
)
fun ContentDTO.toDomain(): Content {
    return Content(
        parts = parts?.map { it.toDomain()}?: emptyList(),
        role = role.orEmpty()
    )
}