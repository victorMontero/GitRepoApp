package com.android.gitrepoapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.gitrepoapp.R
import com.android.gitrepoapp.ui.MainActivity
import com.android.gitrepoapp.ui.RepositoryViewModel

class DetailsFragment : Fragment(R.layout.fagment_details) {

    lateinit var viewModel: RepositoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}