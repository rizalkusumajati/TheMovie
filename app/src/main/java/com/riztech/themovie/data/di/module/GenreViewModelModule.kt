package com.riztech.themovie.data.di.module

import androidx.lifecycle.ViewModel
import com.riztech.themovie.data.di.scope.ViewModelKey
import com.riztech.themovie.presentation.GenreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GenreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GenreViewModel::class)
    abstract fun bindGenreViewModel(viewModel: GenreViewModel): ViewModel
}