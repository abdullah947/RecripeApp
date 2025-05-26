package com.smarthealth.recipe.modifyrecipe.data.api.models.request


import com.smarthealth.recipe.modifyrecipe.domain.models.Content

data class GeminiRequest(
    val contents: List<Content>,
)
