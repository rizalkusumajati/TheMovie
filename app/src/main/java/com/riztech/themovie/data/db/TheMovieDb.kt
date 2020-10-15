package com.riztech.themovie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riztech.themovie.data.model.local.MovieData

@Database(entities = [MovieData.Genre::class, MovieData.Movies::class, MovieData.Detail::class, MovieData.Trailer::class], version = 1, exportSchema = false)
abstract class TheMovieDb : RoomDatabase() {
    abstract fun getGenreDao(): GenreDao
    abstract fun getMovieDao(): MoviesDao
    abstract fun getMovieDetailDao():MoviesDetailDao
    abstract fun getMovieTrailerDao(): TrailerDao
}