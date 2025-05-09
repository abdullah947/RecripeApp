package com.smarthealth.shared.di

import com.smarthealth.network.utils.BaseUrl
import com.smarthealth.network.utils.RetrofitClient
import com.smarthealth.shared.data.api.service.ApiService


import org.koin.dsl.module


val detailRecipeRetrofitModuleMake = module {
    single {
        RetrofitClient.getRetrofit(BaseUrl.SPOON_API)
            .create(ApiService::class.java)
    }
}