package com.smartHealth.recipefinder.di

import android.content.Context
import com.smarthealth.recipe.makerecipe.di.makeRecipeRepoModule
import com.smarthealth.recipe.makerecipe.di.makeRecipeRetrofitModuleMake
import com.smarthealth.recipe.makerecipe.di.makeRecipeViewModelModule
import com.smarthealth.recipe.modifyrecipe.di.modifyRecipeRepoModule
import com.smarthealth.recipe.modifyrecipe.di.modifyRecipeRetrofitModuleMake
import com.smarthealth.recipe.modifyrecipe.di.modifyRecipeViewModelModule
import com.smarthealth.recipe.searchrecipe.di.searchRecipeRepoModule
import com.smarthealth.recipe.searchrecipe.di.searchRecipeRetrofitModule
import com.smarthealth.recipe.searchrecipe.di.searchRecipeViewModelModule
import com.smarthealth.shared.di.detailRecipeRepoModule
import com.smarthealth.shared.di.detailRecipeRetrofitModuleMake
import com.smarthealth.shared.di.detailRecipeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class DIManager private constructor(private val application: Context) {

    companion object {
        fun initialize(application: Context) {
            DIManager(application)
        }
    }

    init {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(getModules())
        }
    }

    private val repoModuleList
        get() = listOf(
            searchRecipeRepoModule,
            makeRecipeRepoModule,
            detailRecipeRepoModule,
            modifyRecipeRepoModule

        )
    private val retrofitModuleList
        get() = listOf(
            searchRecipeRetrofitModule,
            makeRecipeRetrofitModuleMake,
            detailRecipeRetrofitModuleMake,
            modifyRecipeRetrofitModuleMake
        )

    private val viewModelList
        get() = listOf(
            searchRecipeViewModelModule,
            makeRecipeViewModelModule,
            detailRecipeViewModelModule,
            modifyRecipeViewModelModule
        )

    private val moduleList
        get() = listOf(
            viewModelList,
            repoModuleList,
            retrofitModuleList
        )

    private fun getModules(): List<Module> {
        return moduleList.flatten()
    }
}

