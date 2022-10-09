package com.example.keep.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keep.R
import com.example.keep.data.UserViewModel
import com.example.keep.databinding.FragmentSearchBinding
import com.example.keep.fragments.main.ListAdapter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private val adapter = SearchAdapter()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSearchBinding.inflate(inflater, container, false)

        val searchRecyclerView = binding.searchRecyclerView

        searchRecyclerView.adapter = adapter
        searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())

//        UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
//            adapter.setData(user)
//        })

//        val searchView = view.findViewById(R.id.searchView)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.CountrySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    searchDataBase(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null) {
                    searchDataBase(query)
                }
                return false
            }

        })

    }

    private fun searchDataBase(query: String) {
        val searchQuery = "%$query%"

        mUserViewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }
}


