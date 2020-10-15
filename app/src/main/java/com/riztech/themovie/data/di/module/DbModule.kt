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

    @Singleton
    @Provides
    fun provideGenreDao(theMovieDb: TheMovieDb) = theMovieDb.getGenreDao()

    @Singleton
    @Provides
    fun provideMovieDao(theMovieDb: TheMovieDb) = theMovieDb.getMovieDao()

    @Singleton
    @Provides
    fun provideMovieDetailDao(theMovieDb: TheMovieDb) = theMovieDb.getMovieDetailDao()

    @Singleton
    @Provides
    fun provideMovieTrailerDao(theMovieDb: TheMovieDb) = theMovieDb.getMovieTrailerDao()
}