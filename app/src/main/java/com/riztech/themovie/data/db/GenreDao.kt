package com.riztech.themovie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riztech.themovie.data.model.local.MovieData

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun select(): List<MovieData.Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(users: List<MovieData.Genre>)
}