package com.smarthealth.recipe.searchrecipe.di

import com.smarthealth.network.utils.BaseUrl
import com.smarthealth.network.utils.RetrofitClient
import com.smarthealth.recipe.searchrecipe.data.api.service.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val searchRecipeRetrofitModule = module {
    single(named("MealApi")) {
        RetrofitClient.getRetrofit(BaseUrl.MEAL_API)
            .create(ApiService::class.java)
    }
    single(named("SpoonApi")) {
        RetrofitClient.getRetrofit(BaseUrl.SPOON_API)
            .create(ApiService::class.java)
    }
}

