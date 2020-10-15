package com.riztech.themovie.data.di.module

import android.app.Application
import androidx.room.Room
import com.riztech.themovie.data.db.TheMovieDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideMyDatabase(app: Application) = Room.databaseBuilder(app, TheMovieDb::class.java, "tmdb")
        .build()

//    @Singleton
//    @Provides
//    fun provideAlbumDao(theMovieDb: TheMovieDb) = theMovieDb.leagueDao()
}