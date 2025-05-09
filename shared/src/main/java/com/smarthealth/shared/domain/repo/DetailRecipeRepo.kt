package com.smarthealth.shared.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.shared.data.api.models.recipebyid.DetailRecipeResponseDTO


interface DetailRecipeRepo {
    suspend fun getRandomMealsById(
        id: String,
        apiKey: String
    ): NetworkResult<List<DetailRecipeResponseDTO>>
}
