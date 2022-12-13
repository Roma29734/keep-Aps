package com.example.keep.ui.fragments.update

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keep.data.dao.User
import com.example.keep.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    fun updateKeep(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun deleteKeep(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun getTime(): String {
        val dateFormat = SimpleDateFormat("d MMM  HH:mm", Locale.getDefault())
        val data = dateFormat.format(Date())
        Log.d("aboba",data)
        return data
    }
}