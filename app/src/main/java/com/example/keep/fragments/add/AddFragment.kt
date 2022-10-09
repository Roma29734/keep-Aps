package com.example.keep.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.keep.R
import com.example.keep.data.User
import com.example.keep.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            insertDataToDataBase()
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =   inflater.inflate(R.layout.fragment_add, container, false)
//        insertDataToDataBase()
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun insertDataToDataBase(){
        val title = editTitle.text.toString()
        val subTitle = editSubTitle.text.toString()
        val favourites = false


        if(inputCheck(title, subTitle)) {
            val user = User(0, title, subTitle, favourites)
            mUserViewModel.addUser(user)

        } else {
            Toast.makeText(requireContext(), "Поля пусты", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck (title: String, subTitle: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subTitle))
    }
}