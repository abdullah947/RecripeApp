package com.smarthealth.recipe.searchrecipe.di


import com.smarthealth.recipe.searchrecipe.data.repo.SearchRecipeRepoImpl
import com.smarthealth.recipe.searchrecipe.domain.repo.SearchRecipeRepo
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SearchRecipeRepoModule = module {
    single<SearchRecipeRepo> {
        SearchRecipeRepoImpl(get(named("MealApi")), get(named("SpoonApi")))
    }
}

