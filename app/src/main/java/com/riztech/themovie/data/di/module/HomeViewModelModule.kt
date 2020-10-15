package com.riztech.themovie.data.di.module

import androidx.lifecycle.ViewModel
import com.riztech.themovie.data.di.scope.ViewModelKey
import com.riztech.themovie.presentation.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}