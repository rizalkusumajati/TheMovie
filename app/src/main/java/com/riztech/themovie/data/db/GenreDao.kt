package com.riztech.themovie.data.db

import androidx.room.Dao
import androidx.room.Query
import com.riztech.themovie.data.model.local.MovieData

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun select(id: Long): List<MovieData.Genre>
}