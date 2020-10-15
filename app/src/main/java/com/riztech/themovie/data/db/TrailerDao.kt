package com.riztech.themovie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riztech.themovie.data.model.local.MovieData

@Dao
interface TrailerDao {
    @Query("SELECT * FROM video_trailer WHERE id = :movieId")
    fun select(movieId: Int): MovieData.Trailer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesTrailer(video: MovieData.Trailer)
}