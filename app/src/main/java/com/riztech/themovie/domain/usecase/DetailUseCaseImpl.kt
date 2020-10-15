package com.riztech.themovie.domain.usecase

import com.riztech.themovie.data.repository.DetailRepositoryImpl
import com.riztech.themovie.domain.model.MovieDetail
import com.riztech.themovie.domain.model.MovieTrailer
import com.riztech.themovie.domain.model.Review
import com.riztech.themovie.util.Resource
import javax.inject.Inject

class DetailUseCaseImpl @Inject constructor(private val detailRepositoryImpl: DetailRepositoryImpl) : DetailUseCase {
    override suspend fun getDetailMovie(movieId: Int): Resource<MovieDetail> {
        return detailRepositoryImpl.getDetailMovieLocal(movieId)
    }

    override suspend fun getTrailer(movieId: Int): Resource<MovieTrailer?> {
        return detailRepositoryImpl.getMovieTrailerNetwork(movieId)
    }

    override suspend fun getReviews(movieId: Int, page: Int): Resource<List<Review>> {
        return detailRepositoryImpl.getReview(movieId, page)
    }
}