package com.smarthealth.shared.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.shared.data.api.models.recipebyid.toDomain
import com.smarthealth.shared.data.api.service.ApiService
import com.smarthealth.shared.domain.models.recipebyid.DetailRecipeResponse
import com.smarthealth.shared.domain.repo.DetailRecipeRepo

class DetailRecipeRepoImpl(private val apiService: ApiService) : DetailRecipeRepo {

    override suspend fun getRandomMealsById(
        id: String,
        apiKey: String,
    ): NetworkResult<List<DetailRecipeResponse>> {

        return safeApiCall(
            apiCall = { apiService.getAnalyzedInstructions(id, apiKey) },
            mapper = { (it.map { dto -> dto.toDomain() }) }
        )
    }
}
