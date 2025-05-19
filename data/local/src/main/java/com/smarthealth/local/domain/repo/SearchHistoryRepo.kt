package com.smarthealth.local.domain.repo

import com.smarthealth.local.domain.models.SearchHistory

interface SearchHistoryRepo {

    suspend fun insertSearch(search: SearchHistory)

    suspend fun getSuggestions(query: String): List<String>

    suspend fun getAllSearches(): List<SearchHistory>

}