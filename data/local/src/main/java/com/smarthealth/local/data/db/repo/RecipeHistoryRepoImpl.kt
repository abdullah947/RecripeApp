package com.smarthealth.local.data.db.repo

import com.smarthealth.local.data.db.dao.RecipeHistoryDao
import com.smarthealth.local.data.db.models.toDTO
import com.smarthealth.local.data.db.models.toDomain
import com.smarthealth.local.domain.models.RecipeHistory
import com.smarthealth.local.domain.repo.RecipeHistoryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeHistoryRepoImpl(private val recipeDao: RecipeHistoryDao): RecipeHistoryRepo {

    override suspend fun insertRecipe(recipe: RecipeHistory) {
        recipeDao.insertRecipe(recipe.toDTO())
    }

    override fun getAllRecipes(): Flow<List<RecipeHistory>> {
        return recipeDao.getAllRecipes().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun deleteSpecificRecipe(dishId: String) {
        recipeDao.deleteSpecificRecipe(dishId)
    }

    override suspend fun getSpecificRecipe(id: String): RecipeHistory? {
        return recipeDao.getSpecificRecipe(id)?.toDomain()
    }
}



