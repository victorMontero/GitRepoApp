package com.android.gitrepoapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.gitrepoapp.R
import com.android.gitrepoapp.adapters.RepositoryAdapter
import com.android.gitrepoapp.ui.MainActivity
import com.android.gitrepoapp.ui.RepositoryViewModel
import com.android.gitrepoapp.util.Resource
import kotlinx.android.synthetic.main.fagment_repository.*

class RepositoryFragment : Fragment(R.layout.fagment_repository) {

    lateinit var viewModel : RepositoryViewModel
    lateinit var repositoryAdapter : RepositoryAdapter

    val TAG = "RepositoryFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.repositories.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { repositoryResponse ->
                        repositoryAdapter.differ.submitList(repositoryResponse)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let{message ->
                        Toast.makeText(activity, "an error occurred: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar() {
        progress_bar_repository_fragment.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar_repository_fragment.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){
        repositoryAdapter = RepositoryAdapter()
        recycler_view_repository_fragment.apply {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}