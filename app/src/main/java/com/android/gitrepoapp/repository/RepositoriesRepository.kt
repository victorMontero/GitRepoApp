package com.android.gitrepoapp.repository

import com.android.gitrepoapp.api.RetrofitInstance
import com.android.gitrepoapp.db.RepositoryDatabase
import com.android.gitrepoapp.models.RepositoryResponseItem

class RepositoriesRepository(val db: RepositoryDatabase) {

    suspend fun getRepositories() = RetrofitInstance.api.getAllRepositories()

    suspend fun insert(repositoryResponseItem: RepositoryResponseItem) = db.getRepositoryDao().insert(repositoryResponseItem)

    fun getSavedRepositories() = db.getRepositoryDao().getAllRepositories()

    suspend fun deleteRepository(repositoryResponseItem: RepositoryResponseItem) = db.getRepositoryDao().deleteRepository(repositoryResponseItem)
}