package com.smarthealth.local.domain.repo

import com.smarthealth.local.domain.models.RecipeHistory
import kotlinx.coroutines.flow.Flow

interface RecipeHistoryRepo {

    suspend fun insertRecipe(recipe: RecipeHistory)

    fun getAllRecipes(): Flow<List<RecipeHistory>>

    suspend fun deleteSpecificRecipe(dishId: String)

    suspend fun getSpecificRecipe(id: String): RecipeHistory?
}