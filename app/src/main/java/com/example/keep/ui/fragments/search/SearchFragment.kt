package com.example.keep.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keep.base.BaseFragment
import com.example.keep.databinding.FragmentSearchBinding
import com.example.keep.ui.fragments.main.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SearchAdapter(viewModel::onSomeClick)

        binding.searchRecyclerView.adapter = adapter

        viewModel.searchResult.observe(viewLifecycleOwner) {result ->
            adapter.setData(result)
        }
        binding.include6.SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    viewModel.searchMovie(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null) {
                    viewModel.searchMovie(query)
                }
                return false
            }

        })

        binding.include6.iBBack.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

    }
}


