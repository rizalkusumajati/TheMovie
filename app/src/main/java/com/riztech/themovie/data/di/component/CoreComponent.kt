package com.riztech.themovie.data.di.component

import android.app.Application
import android.content.SharedPreferences
import com.riztech.themovie.data.api.MovieApi
import com.riztech.themovie.data.db.GenreDao
import com.riztech.themovie.data.db.MoviesDao
import com.riztech.themovie.data.db.MoviesDetailDao
import com.riztech.themovie.data.db.TrailerDao
import com.riztech.themovie.data.di.module.DbModule
import com.riztech.themovie.data.di.module.NetworkModule
import com.riztech.themovie.data.di.module.SharePrefModule
import com.riztech.themovie.data.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharePrefModule::class, NetworkModule::class, DbModule::class, ViewModelModule::class])
interface CoreComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun sharedPrefModule(sharePrefModule: SharePrefModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun dbModule(dbModule: DbModule): Builder
        fun build(): CoreComponent
    }

    fun provideApp(): Application
    //
    fun getSharedPreference(): SharedPreferences

    fun getMovieApi(): MovieApi

    fun getGenreDao(): GenreDao

    fun getMovieDao(): MoviesDao

    fun getMovieDetailDao(): MoviesDetailDao

    fun getTrailerDao(): TrailerDao

}