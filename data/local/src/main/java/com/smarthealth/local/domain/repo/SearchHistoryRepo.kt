package com.smarthealth.local.domain.repo

import com.smarthealth.local.data.db.models.SearchHistoryDTO

interface SearchHistoryRepo {


    suspend fun insertSearch(search: SearchHistoryDTO)

    suspend fun getSuggestions(query: String): List<String>

}