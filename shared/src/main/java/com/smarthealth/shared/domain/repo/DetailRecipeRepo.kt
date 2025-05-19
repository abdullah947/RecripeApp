package com.smarthealth.shared.domain.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.shared.domain.models.recipebyid.DetailRecipeResponse

interface DetailRecipeRepo {
    suspend fun getRandomMealsById(
        id: String,
        apiKey: String,
    ): NetworkResult<List<DetailRecipeResponse>>
}
