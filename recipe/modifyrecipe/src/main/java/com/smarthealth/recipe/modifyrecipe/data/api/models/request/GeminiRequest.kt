package com.smarthealth.recipe.modifyrecipe.data.api.models.request

import com.smarthealth.recipe.modifyrecipe.data.api.models.response.ContentDTO

data class GeminiRequest(
    val contents: List<ContentDTO>,
)
