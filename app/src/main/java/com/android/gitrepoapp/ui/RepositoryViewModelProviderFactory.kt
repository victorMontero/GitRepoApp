package com.android.gitrepoapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.gitrepoapp.models.RepositoryResponseItem
import com.android.gitrepoapp.repository.RepositoriesRepository

class RepositoryViewModelProviderFactory(val app: Application, val repositoriesRepository: RepositoriesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoryViewModel(app, repositoriesRepository) as T
    }
}