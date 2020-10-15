package com.riztech.themovie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.riztech.themovie.domain.usecase.HomeUseCaseImpl
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCaseImpl: HomeUseCaseImpl) : ViewModel() {

    fun test(){
        Log.d("TEST", "TEST VM")
    }
}