package com.smarthealth.local.data.db.repo

import com.smarthealth.local.data.db.dao.SearchHistoryDao
import com.smarthealth.local.data.db.models.toDTO
import com.smarthealth.local.data.db.models.toDomain
import com.smarthealth.local.domain.models.SearchHistory
import com.smarthealth.local.domain.repo.SearchHistoryRepo

class SearchHistoryRepoImpl(private val searchesDao: SearchHistoryDao) : SearchHistoryRepo {
    override suspend fun insertSearch(search: SearchHistory) {
        searchesDao.insertSearch(search.toDTO())
    }

    override suspend fun getSuggestions(query: String): List<String> {
        return searchesDao.getSuggestions(query)
    }

    override suspend fun getAllSearches(): List<SearchHistory> {
        return searchesDao.getAllSearches().map { list ->
            list.toDomain()
        }
    }

}


