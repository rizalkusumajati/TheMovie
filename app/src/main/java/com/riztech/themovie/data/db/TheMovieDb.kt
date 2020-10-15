package com.riztech.themovie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riztech.themovie.data.model.local.MovieData

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
abstract class TheMovieDb : RoomDatabase() {
}