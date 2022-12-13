package com.example.keep.data.repository

import androidx.lifecycle.LiveData
import com.example.keep.data.dao.User
import com.example.keep.data.dao.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {

    fun readAllData(): LiveData<List<User>>

    suspend fun addUser(user: User): Long

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun searchDatabase(searchQuery: String): List<User>

    fun readFavouritesData(): LiveData<List<User>>
}

