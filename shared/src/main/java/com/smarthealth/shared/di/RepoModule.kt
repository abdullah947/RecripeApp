package com.smarthealth.shared.di

import com.smarthealth.shared.data.repo.DetailRecipeRepoImpl
import com.smarthealth.shared.domain.repo.DetailRecipeRepo
import org.koin.dsl.module

val detailRecipeRepoModule = module {
    single<DetailRecipeRepo> {
        DetailRecipeRepoImpl(get())
    }
}


