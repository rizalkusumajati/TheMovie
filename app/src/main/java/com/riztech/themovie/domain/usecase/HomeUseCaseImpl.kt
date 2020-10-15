package com.riztech.themovie.domain.usecase

import com.riztech.themovie.data.repository.HomeRepositoryImpl
import com.riztech.themovie.domain.model.Genre
import com.riztech.themovie.util.Resource
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(private val homeRepositoryImpl: HomeRepositoryImpl) : HomeUseCase {

    override suspend fun getGenre(): Resource<List<Genre>> {
       return homeRepositoryImpl.getGenreLocal()
    }
}