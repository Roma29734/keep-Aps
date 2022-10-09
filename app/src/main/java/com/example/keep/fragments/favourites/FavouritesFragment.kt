package com.example.keep.fragments.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keep.data.UserViewModel
import com.example.keep.databinding.FragmentFavouritesBinding



class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var adapter: FavouritesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

// _binding =  FragmentSearchBinding.inflate(inflater, container, false)

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val recyclerView = binding.recyclerFavourites

        adapter = FavouritesAdapter(mUserViewModel::onSomeClick)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel.readFavouritesData.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }

        return binding.root
    }
}