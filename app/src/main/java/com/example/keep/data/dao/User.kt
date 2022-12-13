package com.example.keep.data.dao

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "subTitle")val subTitle: String,
    @ColumnInfo(name = "favourites")var favourites: Boolean,
    @ColumnInfo(name = "date")val date: String,
): Parcelable
