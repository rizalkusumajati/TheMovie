package com.riztech.themovie.data.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharePrefModule {

    @Singleton
    @Provides
    fun providePreference(app: Application): SharedPreferences {
        return app.getSharedPreferences("themovie", Context.MODE_PRIVATE)
    }
}