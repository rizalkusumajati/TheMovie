package com.riztech.themovie.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class MovieData {

    @Entity(tableName = "genres")
    data class Genre(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Long,
                     @ColumnInfo(name = "name") val name: String) : MovieData()


}