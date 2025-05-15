package com.smarthealth.local.di

import com.smarthealth.local.domain.repo.RecipeHistoryRepo
import com.smarthealth.local.data.db.repo.RecipeHistoryRepoImpl
import com.smarthealth.local.data.db.repo.SearchHistoryRepoImpl
import com.smarthealth.local.domain.repo.SearchHistoryRepo
import org.koin.dsl.module

val databaseRepoModule = module {
    single<RecipeHistoryRepo> {
        RecipeHistoryRepoImpl(get())
    }

    single<SearchHistoryRepo> {
        SearchHistoryRepoImpl(get())
    }

}
