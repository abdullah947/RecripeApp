package com.smarthealth.shared.di

import androidx.lifecycle.SavedStateHandle
import com.smarthealth.shared.presentation.detail.DetailRecipeViewModel
import com.smarthealth.shared.presentation.viewmodels.MainActivitySharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailRecipeViewModelModule = module {
    viewModel { (stateHandle: SavedStateHandle) ->
        DetailRecipeViewModel(get(),get(), stateHandle)
    }
    viewModel {
        MainActivitySharedViewModel()
    }
}