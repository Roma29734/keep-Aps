package com.example.keep.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.keep.R
import com.example.keep.base.BaseFragment
import com.example.keep.databinding.FragmentHomeBinding
import com.example.keep.ui.MainActivity
import com.example.keep.ui.fragments.favourites.FavouritesFragment
import com.example.keep.ui.fragments.main.MainFragment
import com.example.keep.ui.fragments.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        set()

        binding.include.imageFavourites.setOnClickListener {
            val state = (requireActivity() as MainActivity).readDate()
            val update = (requireActivity() as MainActivity)
            if(!state) {
                update.updateDate(true)
                set()
            } else {
                update.updateDate(false)
                set()
            }
        }

        binding.include.floatingActionButtonAddKeep.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addFragment)
        }

        binding.include.imageSearch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    fun set() {
        val readlState = (requireActivity() as MainActivity).readDate()
        if(readlState) {
            setCurrentFragment(FavouritesFragment())
            binding.include.imageFavourites.setImageResource(R.drawable.ic_star_icon_select)
        } else{
            setCurrentFragment(MainFragment())
            binding.include.imageFavourites.setImageResource(R.drawable.ic_star_icon)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentView,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}