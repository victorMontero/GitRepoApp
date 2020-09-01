package com.android.gitrepoapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.gitrepoapp.RepositoryApplication
import com.android.gitrepoapp.models.RepositoryResponse
import com.android.gitrepoapp.repository.RepositoriesRepository
import com.android.gitrepoapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class RepositoryViewModel(app: Application, val repositoryRepository: RepositoriesRepository) : AndroidViewModel(app) {

    val repositories : MutableLiveData<Resource<RepositoryResponse>> = MutableLiveData()

    init {
        getRepositories()
    }

    private fun getRepositories() = viewModelScope.launch {
        safeRepositoriesCall()
    }

    private suspend fun safeRepositoriesCall() {
        repositories.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()){
                val response = repositoryRepository.getRepositories()
                repositories.postValue(handleRepositoryResponse(response))
            } else {
                repositories.postValue(Resource.Error("no internet"))
            }
        } catch (t: Throwable){
            when(t){
                is IOException -> repositories.postValue(Resource.Error("network failure"))
                else -> repositories.postValue(Resource.Error("conversion error"))
            }
        }
    }

    private fun handleRepositoryResponse(response: Response<RepositoryResponse>): Resource<RepositoryResponse>? {
        if (response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<RepositoryApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false

            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}