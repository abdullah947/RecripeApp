package com.smarthealth.local.domain.repo

import com.smarthealth.local.data.db.models.RecipeHistoryDTO
import kotlinx.coroutines.flow.Flow

interface RecipeHistoryRepo {


    suspend fun insertRecipe(recipe: RecipeHistoryDTO)

    fun getAllRecipes(): Flow<List<RecipeHistoryDTO>>

    suspend fun deleteSpecificRecipe(dishId: String)

    suspend fun getSpecificRecipe(id: String): RecipeHistoryDTO?
}