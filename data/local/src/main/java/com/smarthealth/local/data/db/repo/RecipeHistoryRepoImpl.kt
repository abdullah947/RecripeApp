package com.smarthealth.local.data.db.repo

import com.smarthealth.local.data.db.dao.RecipeHistoryDao
import com.smarthealth.local.data.db.models.RecipeHistoryDTO
import com.smarthealth.local.data.db.models.toDomain
import com.smarthealth.local.data.db.models.toEntity
import com.smarthealth.local.domain.models.RecipeHistory
import com.smarthealth.local.domain.repo.RecipeHistoryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeHistoryRepoImpl(private val recipeDao: RecipeHistoryDao): RecipeHistoryRepo {

    override suspend fun insertRecipe(recipe: RecipeHistoryDTO) {
        recipeDao.insertRecipe(recipe)
    }

    override fun getAllRecipes(): Flow<List<RecipeHistoryDTO>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun deleteSpecificRecipe(dishId: String) {
        recipeDao.deleteSpecificRecipe(dishId)
    }

    override suspend fun getSpecificRecipe(id: String): RecipeHistoryDTO? {
        return recipeDao.getSpecificRecipe(id)
    }
}


/*
class RecipeHistoryRepoImpl(private val recipeDao: RecipeHistoryDao): RecipeHistoryRepo {

    override suspend fun insertRecipe(recipe: RecipeHistory) {
        recipeDao.insertRecipe(recipe.toEntity())
    }

    override fun getAllRecipes(): Flow<List<RecipeHistory>> {
        return recipeDao.getAllRecipes().map {
            it.map { it.toDomain() }
        }
    }

    override suspend fun deleteSpecificRecipe(dishId: String) {
        recipeDao.deleteSpecificRecipe(dishId)
    }

    override suspend fun getSpecificRecipe(id: String): RecipeHistory {
        return recipeDao.getSpecificRecipe(id)?.toDomain()
    }
}*/
