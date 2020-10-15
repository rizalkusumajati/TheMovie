package com.riztech.themovie.domain.usecase

import com.riztech.themovie.domain.model.MovieDetail
import com.riztech.themovie.domain.model.MovieTrailer
import com.riztech.themovie.domain.model.Review
import com.riztech.themovie.util.Resource

interface DetailUseCase {
    suspend fun getDetailMovie(movieId: Int): Resource<MovieDetail>
    suspend fun getTrailer(movieId: Int): Resource<MovieTrailer?>
    suspend fun getReviews(movieId: Int, page: Int): Resource<List<Review>>
}