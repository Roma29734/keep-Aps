package com.example.keep.data.repository

import androidx.lifecycle.LiveData
import com.example.keep.data.dao.User
import com.example.keep.data.dao.UserDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
): UserRepository {
    override fun readAllData(): LiveData<List<User>> {
        return dao.readAllData()
    }

    override suspend fun addUser(user: User): Long {
        return dao.addUser(user)
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }

    override suspend fun searchDatabase(searchQuery: String): List<User> {
        return dao.searchDatabase(searchQuery)
    }

    override fun readFavouritesData(): LiveData<List<User>> {
        return dao.readFavouritesData()
    }
}