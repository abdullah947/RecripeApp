package com.smarthealth.recipe.searchrecipe.di

import com.smarthealth.recipe.searchrecipe.presentation.search.SearchRecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchRecipeViewModelModule = module {
    viewModel { SearchRecipeViewModel(get(),get(),get())}
}

