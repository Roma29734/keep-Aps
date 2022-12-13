package com.example.keep.ui.fragments.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keep.base.BaseFragment
import com.example.keep.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include3.mainTitle.text = "Избранное"
        val adapter = FavouritesAdapter(viewModel::onSomeClick)
        binding.recyclerFavourites.adapter = adapter
        viewModel.favoriteUser.observe(viewLifecycleOwner) {date ->
            adapter.setData(date)
        }
    }
}