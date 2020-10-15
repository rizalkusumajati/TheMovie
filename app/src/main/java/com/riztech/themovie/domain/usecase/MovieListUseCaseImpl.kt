package com.riztech.themovie.domain.usecase

import com.riztech.themovie.data.repository.MovieListRepositoryImpl
import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.util.Resource
import javax.inject.Inject

class MovieListUseCaseImpl @Inject constructor(private val movieListRepositoryImpl: MovieListRepositoryImpl) : MovieListUseCase {
    override suspend fun getMovieByGenre(page: Int, genreId: Int): Resource<List<HomeMovie>> {
        return movieListRepositoryImpl.getMovieByGenreNetwork(page, genreId)
    }
}