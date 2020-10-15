package com.riztech.themovie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
abstract class TheMovieDb : RoomDatabase() {
}