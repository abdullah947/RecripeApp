package com.smarthealth.recipe.modifyrecipe.di


import com.smarthealth.recipe.modifyrecipe.presentation.modify.ModifyRecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modifyRecipeViewModelModule = module {
    viewModel { ModifyRecipeViewModel(get()) }
}





