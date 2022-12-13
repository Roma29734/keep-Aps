package com.example.keep.ui.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.keep.base.BaseFragment
import com.example.keep.data.dao.User
import com.example.keep.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add.*

import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {

    private val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            insertDataToDataBase()
            view?.let { Navigation.findNavController(it).popBackStack() }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include4.mainTitle.text = "Новая заметка"
        binding.include4.iBBack.setOnClickListener {
            insertDataToDataBase()
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun insertDataToDataBase(){
        val title = editTitle.text.toString()
        val subTitle = editSubTitle.text.toString()
        val favourites = false

        if(inputCheck(title, subTitle)) {
            val user = User(0, title, subTitle, favourites, viewModel.getTime())
            viewModel.addKeep(user)
        } else {
            Toast.makeText(requireContext(), "Поля пусты", Toast.LENGTH_SHORT).show()
        }
    }
}