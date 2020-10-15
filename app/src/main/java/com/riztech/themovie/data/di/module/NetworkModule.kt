package com.riztech.themovie.data.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import com.riztech.themovie.BuildConfig
import com.riztech.themovie.data.api.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module (includes = [ViewModelModule::class])
class NetworkModule() {

    @Provides
    fun provideOkhttpClient(mainApp: Application): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Named("tmdb_rest")
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        builder.client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
        return builder.build()
    }
//
    @Provides
    fun provideMovieDbServices(@Named("tmdb_rest") restAdapter: Retrofit): MovieApi {
        return restAdapter.create<MovieApi>(MovieApi::class.java)
    }

}