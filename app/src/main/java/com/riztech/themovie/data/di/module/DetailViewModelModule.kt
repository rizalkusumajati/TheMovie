package com.riztech.themovie.data.di.module

import androidx.lifecycle.ViewModel
import com.riztech.themovie.data.di.scope.ViewModelKey
import com.riztech.themovie.presentation.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}