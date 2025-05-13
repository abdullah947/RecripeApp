package com.smarthealth.recipe.modifyrecipe.di

import com.smarthealth.network.utils.BaseUrl
import com.smarthealth.network.utils.RetrofitClient
import com.smarthealth.recipe.modifyrecipe.data.api.service.ApiService
import org.koin.dsl.module


val modifyRecipeRetrofitModuleMake = module {
    single {
        RetrofitClient.getRetrofit(BaseUrl.GEMINI_API)
            .create(ApiService::class.java)
    }
}