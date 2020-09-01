package com.android.gitrepoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.gitrepoapp.models.RepositoryResponseItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositoryResponseItem: RepositoryResponseItem): Long

    @Query("SELECT * FROM repositories")
    fun getAllRepositories(): LiveData<List<RepositoryResponseItem>>

    @Delete
    suspend fun deleteRepository(repositoryResponseItem: RepositoryResponseItem)

}