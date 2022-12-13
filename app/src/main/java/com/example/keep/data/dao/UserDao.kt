package com.example.keep.data.dao



import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long

    @Update(entity = User::class)
    suspend fun updateUser(user: User)

    @Delete(entity = User::class)
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>
//    LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE title LIKE :searchQuery OR subTitle LIKE :searchQuery")
    suspend fun searchDatabase(searchQuery: String) : List<User>

//    @Query("SELECT * FROM user_table WHERE favourites = 1 ORDER BY id ASC")
//    fun readFavouritesData(user: User): LiveData<List<User>>
    @Query("SELECT * FROM user_table WHERE favourites = 1 ORDER BY id ASC")
    fun readFavouritesData(): LiveData<List<User>>
}
