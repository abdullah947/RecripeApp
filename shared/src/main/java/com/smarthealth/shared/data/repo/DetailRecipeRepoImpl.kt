package com.smarthealth.shared.data.repo

import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.network.utils.safeApiCall
import com.smarthealth.shared.data.api.models.recipebyid.DetailRecipeResponseDTO
import com.smarthealth.shared.data.api.models.recipebyid.toDomain
import com.smarthealth.shared.data.api.service.ApiService
import com.smarthealth.shared.domain.models.recipebyid.DetailRecipeResponse
import com.smarthealth.shared.domain.repo.DetailRecipeRepo

class DetailRecipeRepoImpl(private val apiService: ApiService) : DetailRecipeRepo {

    override suspend fun getRandomMealsById(
        id: String,
        apiKey: String
    ): NetworkResult<List<DetailRecipeResponse>> {

        val result: NetworkResult<List<DetailRecipeResponseDTO>> = safeApiCall {
            apiService.getAnalyzedInstructions(id, apiKey)
        }
        return when (result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    NetworkResult.Success(it.map { dto -> dto.toDomain() })
                } ?: NetworkResult.Error("Empty response")
            }
            is NetworkResult.Error -> NetworkResult.Error(result.message ?: "Unknown error")
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }
}
