package com.smarthealth.recipe.makerecipe.di


import com.smarthealth.recipe.makerecipe.data.repo.MakeRecipeRepoImpl
import com.smarthealth.recipe.makerecipe.domain.repo.MakeRecipeRepo
import org.koin.dsl.module

val makeRecipeRepoModule = module {
    single<MakeRecipeRepo> {
        MakeRecipeRepoImpl(get())
    }
}


