package com.android.gitrepoapp.api

import com.android.gitrepoapp.models.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface RepositoryAPI {

    @GET("repositories")
    suspend fun getAllRepositories(): Response<RepositoryResponse>
}