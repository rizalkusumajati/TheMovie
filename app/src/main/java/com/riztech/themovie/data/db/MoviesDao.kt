package com.riztech.themovie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riztech.themovie.data.model.local.MovieData

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun select(): List<MovieData.Movies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(users: List<MovieData.Movies>)
}