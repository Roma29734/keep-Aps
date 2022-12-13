package com.example.keep.ui.fragments.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keep.data.dao.User
import com.example.keep.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    var favoriteUser: LiveData<List<User>> = repository.readFavouritesData()


    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun onSomeClick(itemId: Int, title: String, subTitle: String, favourites: Boolean, date: String): Unit {
        Log.d("viewModel", "Обработка нажатия favourites")
        if(favourites) {
            val update = User(itemId, title, subTitle, false, date)
            updateUser(update)

        } else {
            val update = User(itemId, title, subTitle, true, date)
            updateUser(update)
        }
    }
}