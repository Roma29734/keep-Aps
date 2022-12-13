package com.example.keep.ui.fragments.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
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
class AddViewModel @Inject constructor(
    private val repository: UserRepository,
): ViewModel() {

    val userId: MutableLiveData<Long?> = MutableLiveData()

    fun addKeep(user: User){
       viewModelScope.launch {
           userId.value = repository.addUser(user)
           Log.d("addFragment", "id: ${userId.value}")
       }
    }

    fun getTime(): String {
        val dateFormat = SimpleDateFormat("d MMM  HH:mm", Locale.getDefault())
        val data = dateFormat.format(Date())
        Log.d("aboba",data)
        return data
    }
}
