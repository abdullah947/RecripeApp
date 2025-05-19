package com.smarthealth.recipe.makerecipe.di

import com.smarthealth.recipe.makerecipe.presentation.make.MakeRecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val makeRecipeViewModelModule = module {
    viewModel { MakeRecipeViewModel(get()) }
}





