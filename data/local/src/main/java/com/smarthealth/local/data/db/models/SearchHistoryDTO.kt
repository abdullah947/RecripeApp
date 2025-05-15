package com.smarthealth.local.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smarthealth.local.domain.models.SearchHistory


@Entity(tableName = "Searches_table")
data class SearchHistoryDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val searchText: String,
)

fun SearchHistoryDTO.toDomain(): SearchHistory {
    return SearchHistory(
        id = id,
        searchText = searchText
    )
}

fun SearchHistory.toEntity():SearchHistoryDTO{
    return SearchHistoryDTO(
        id = id,
        searchText = searchText
    )
}