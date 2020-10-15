package com.riztech.themovie.data.api

import com.riztech.themovie.BuildConfig
import com.riztech.themovie.data.model.network.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(@Query("api_key") apiKey: String = BuildConfig.TMDB_implementation_KEY) : GenreResponse

    @GET("discover/movie")
    suspend fun getMovieByGenres(@Query("api_key") apiKey: String = BuildConfig.TMDB_implementation_KEY, @Query("page") page: Int, @Query("with_genres") genreId: Int): MovieByGenre

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_implementation_KEY ): MovieDetail

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsMovie(@Path("movie_id") movieId: Int, @Query("page") page: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_implementation_KEY): Reviews

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailerMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String = BuildConfig.TMDB_implementation_KEY): Video
    
}