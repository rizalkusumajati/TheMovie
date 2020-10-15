package com.riztech.themovie.data.api

import com.riztech.themovie.data.model.network.GenreResponse
import com.riztech.themovie.data.model.network.MovieByGenre
import com.riztech.themovie.data.model.network.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getMovieGenres() : GenreResponse

    @GET("discover/movie")
    suspend fun getMovieByGenres(@Query("page") page: Int, @Query("with_genres") genreId: Int): MovieByGenre

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetail

    @GET("movie/{movie_id}/review")
    suspend fun getReviewsMovie(@Path("movie_id") movieId: Int, @Query("page") page: Int)

    
}