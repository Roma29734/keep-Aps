package com.example.keep.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.addCallback
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.keep.R
import com.example.keep.base.BaseFragment
import com.example.keep.data.dao.User
import com.example.keep.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding>(FragmentUpdateBinding::inflate) {

    private val args by navArgs<UpdateFragmentArgs>()
    private val viewModel: UpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            updateData()
            view?.let { Navigation.findNavController(it).popBackStack() }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.updateTitle.setText(args.currentUser.title)
        binding.updateSubTitle.setText(args.currentUser.subTitle)

        binding.include5.openMenu.setOnClickListener {
            val popupMenu = PopupMenu(context, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.menu_delete -> {
                        deleteUser()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.opens_menu)
            popupMenu.show()
        }
        binding.include5.iBBack.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun updateData() {
        val title = updateTitle.text.toString()
        val subTitle = updateSubTitle.text.toString()

        if(inputCheck(title, subTitle)) {
            val update = User(args.currentUser.id, title, subTitle, args.currentUser.favourites, viewModel.getTime())

            viewModel.updateKeep(update)
        }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
        viewModel.deleteKeep(args.currentUser)
            Toast.makeText(
                requireContext(),
                "успешно удалено: ",
                Toast.LENGTH_SHORT).show()
            view?.let { Navigation.findNavController(it).popBackStack() }
        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Удалить ?")
        builder.setMessage("Вы уверены в своем действи ?")
        builder.create().show()
    }

}


