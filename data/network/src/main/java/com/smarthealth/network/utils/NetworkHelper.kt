package com.smarthealth.network.utils

import retrofit2.Response

suspend fun <T,R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): NetworkResult<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                NetworkResult.Success(mapper(body))
            } else {
                NetworkResult.Error("Response  is null")
            }
        } else {
            NetworkResult.Error(response.message())
        }
    } catch (e: Exception) {
        NetworkResult.Error(e.message ?: "Unknown error")
    }
}