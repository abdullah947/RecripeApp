package com.smarthealth.local.data.db.repo

import com.smarthealth.local.data.db.dao.SearchHistoryDao
import com.smarthealth.local.data.db.models.SearchHistoryDTO
import com.smarthealth.local.data.db.models.toEntity
import com.smarthealth.local.domain.models.SearchHistory
import com.smarthealth.local.domain.repo.SearchHistoryRepo

class SearchHistoryRepoImpl(private val searchesDao: SearchHistoryDao): SearchHistoryRepo {
     override suspend fun insertSearch(search: SearchHistoryDTO) {
         searchesDao.insertSearch(search)
     }


    override suspend fun getSuggestions(query: String): List<String> {
        return searchesDao.getSuggestions(query)
     }


 }

/*
class SearchHistoryRepoImpl(private val searchesDao: SearchHistoryDao): SearchHistoryRepo {
    override suspend fun insertSearch(search: SearchHistory) {
        searchesDao.insertSearch(search.toEntity())
    }

    override suspend fun getSuggestions(query: String): List<String> {
        return searchesDao.getSuggestions(query)
    }


}
*/
