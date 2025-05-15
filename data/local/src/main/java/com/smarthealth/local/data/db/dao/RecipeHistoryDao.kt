package com.smarthealth.local.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smarthealth.local.data.db.models.RecipeHistoryDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeHistoryDTO)


    @Query("SELECT * FROM Recipes_table ORDER BY id DESC")
    fun getAllRecipes(): Flow<List<RecipeHistoryDTO>>

    @Query("DELETE FROM Recipes_table WHERE dishId = :dishId")
    suspend fun deleteSpecificRecipe(dishId: String)

    @Query("SELECT * FROM Recipes_table WHERE dishId = :dishId")
    suspend fun getSpecificRecipe(dishId: String): RecipeHistoryDTO?
}
