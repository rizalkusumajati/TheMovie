package com.riztech.themovie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riztech.themovie.data.model.local.MovieData

@Dao
interface MoviesDetailDao {
    @Query("SELECT * FROM detail_movie WHERE id = :movieId")
    fun select(movieId: Int): MovieData.Detail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesDetail(movie: MovieData.Detail)
}