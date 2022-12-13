package com.example.keep.di

import android.content.Context
import androidx.room.Room
import com.example.keep.data.UserDataBase
import com.example.keep.data.dao.UserDao
import com.example.keep.data.repository.UserRepository
import com.example.keep.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class baseModule {

    @Provides
    fun provideRepository(impl: UserRepositoryImpl): UserRepository = impl

    @Provides
    fun provideMovieDao(appDataBase: UserDataBase): UserDao = appDataBase.userDao()

    @Provides
    @Singleton
    fun provideMovieDataBase(@ApplicationContext appContext: Context): UserDataBase =
        Room.databaseBuilder(
            appContext,
            UserDataBase::class.java,
            "user_table"
        ).build()
}