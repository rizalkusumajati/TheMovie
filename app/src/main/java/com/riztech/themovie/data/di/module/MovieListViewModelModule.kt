package com.riztech.themovie.data.di.module

import androidx.lifecycle.ViewModel
import com.riztech.themovie.data.di.scope.ViewModelKey
import com.riztech.themovie.presentation.HomeViewModel
import com.riztech.themovie.presentation.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel
}