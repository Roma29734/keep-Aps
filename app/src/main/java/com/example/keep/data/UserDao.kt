package com.example.keep.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE title LIKE :searchQuery OR subTitle LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Flow<List<User>>

//    @Query("SELECT * FROM user_table WHERE favourites = 1 ORDER BY id ASC")
//    fun readFavouritesData(user: User): LiveData<List<User>>
    @Query("SELECT * FROM user_table WHERE favourites = 1 ORDER BY id ASC")
    fun readFavouritesData(): LiveData<List<User>>

}
