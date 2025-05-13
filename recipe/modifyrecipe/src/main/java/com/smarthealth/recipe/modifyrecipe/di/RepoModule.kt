package com.smarthealth.recipe.modifyrecipe.di



import com.smarthealth.recipe.modifyrecipe.data.repo.ModifyRecipeRepoImpl
import com.smarthealth.recipe.modifyrecipe.domain.repo.ModifyRecipeRepo
import org.koin.dsl.module

val modifyRecipeRepoModule = module {
    single<ModifyRecipeRepo> {
        ModifyRecipeRepoImpl(get())
    }
}

