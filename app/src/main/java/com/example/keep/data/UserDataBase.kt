package com.example.keep.data

import android.content.Context
import androidx.room.Database

import androidx.room.RoomDatabase
import com.example.keep.data.dao.User
import com.example.keep.data.dao.UserDao


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase (){
    abstract fun userDao() : UserDao
}
