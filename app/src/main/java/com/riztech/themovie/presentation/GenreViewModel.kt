package com.riztech.themovie.presentation

import androidx.lifecycle.ViewModel
import com.riztech.themovie.domain.usecase.GenreUseCaseImpl
import javax.inject.Inject

class GenreViewModel @Inject constructor(private val genreUseCaseImpl: GenreUseCaseImpl): ViewModel() {
}