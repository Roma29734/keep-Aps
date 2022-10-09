package com.example.keep.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    val readFavouritesData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = UserRepository(userDao)
        readFavouritesData =repository.readFavouritesData
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>> {
        return  repository.searchDatabase(searchQuery).asLiveData()
    }

//    fun readFavouritesData(user: User): LiveData<List<User>> {
//        return repository.readFavouritesData(user)
//    }

    fun onSomeClick(itemId: Int, title: String, subTitle: String, favourites: Boolean): Unit {
        Log.d("viewModel", "Обработка нажатия favourites")
        if(favourites) {
            val update = User(itemId, title, subTitle, false)
            updateUser(update)
        } else {
            val update = User(itemId, title, subTitle, true)
            updateUser(update)
        }
    }

}
