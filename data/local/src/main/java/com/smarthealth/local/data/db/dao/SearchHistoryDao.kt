package com.smarthealth.local.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smarthealth.local.data.db.models.SearchHistoryDTO

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(recipe: SearchHistoryDTO)


    @Query("SELECT searchText FROM Searches_table WHERE searchText LIKE '%' || :query || '%' ")
    suspend fun getSuggestions(query: String): List<String>

}
