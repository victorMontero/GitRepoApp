package com.android.gitrepoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.gitrepoapp.R
import com.android.gitrepoapp.db.RepositoryDatabase
import com.android.gitrepoapp.repository.RepositoriesRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RepositoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repositoriesRepository = RepositoriesRepository(RepositoryDatabase(this))
        val viewModelProviderFactory = RepositoryViewModelProviderFactory(application, repositoriesRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RepositoryViewModel::class.java)


    }
}