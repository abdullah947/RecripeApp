package com.smarthealth.recipe.makerecipe.di

import com.smarthealth.network.utils.BaseUrl
import com.smarthealth.network.utils.RetrofitClient
import com.smarthealth.recipe.makerecipe.data.api.service.ApiService

import org.koin.dsl.module


val makeRecipeRetrofitModuleMake = module {
    single {
        RetrofitClient.getRetrofit(BaseUrl.SPOON_API)
            .create(ApiService::class.java)
    }
}