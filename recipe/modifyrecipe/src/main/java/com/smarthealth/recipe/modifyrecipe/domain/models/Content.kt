package com.smarthealth.recipe.modifyrecipe.domain.models

data class Content(
    val parts: List<Part>,
    val role: String
)