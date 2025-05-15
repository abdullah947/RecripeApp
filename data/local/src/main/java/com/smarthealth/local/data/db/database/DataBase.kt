package com.smarthealth.local.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smarthealth.local.data.db.dao.RecipeHistoryDao
import com.smarthealth.local.data.db.dao.SearchHistoryDao
import com.smarthealth.local.data.db.models.RecipeHistoryDTO
import com.smarthealth.local.data.db.models.SearchHistoryDTO

@Database(entities = [RecipeHistoryDTO::class,SearchHistoryDTO::class], version = 1, exportSchema = false)
abstract class DataBase:RoomDatabase() {
    abstract fun recipeHistoryDao():RecipeHistoryDao
    abstract fun searchHistoryDao():SearchHistoryDao
}

