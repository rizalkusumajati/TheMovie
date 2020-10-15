package com.riztech.themovie.presentation

import androidx.lifecycle.ViewModel
import com.riztech.themovie.domain.usecase.DetailUseCaseImpl
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val detailUseCaseImpl: DetailUseCaseImpl) : ViewModel() {
}