package com.example.keep.fragments.main

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keep.R
import com.example.keep.data.User
import com.example.keep.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var adapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val recyclerView = view.recyclerView

        adapter = ListAdapter(mUserViewModel::onSomeClick)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        UserViewModel
        mUserViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNewKep: FloatingActionButton = view.findViewById(R.id.floatingActionButtonAddKeep)
        val buttonSearch: ImageView = view.findViewById(R.id.imageSearch)
        val buttonFavourites: ImageView = view.findViewById(R.id.imageFavourites)

        buttonNewKep.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addFragment)
        }

        buttonSearch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_searchFragment)
        }

        buttonFavourites.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_favouritesFragment)
        }
    }
}


