package com.riztech.themovie.data.di.component

import com.riztech.themovie.data.di.module.HomeViewModelModule
import com.riztech.themovie.data.di.module.MovieListViewModelModule
import com.riztech.themovie.data.di.scope.HomeScope
import com.riztech.themovie.data.di.scope.MovieListScope
import com.riztech.themovie.presentation.MovieListFragment
import dagger.Component

@MovieListScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [MovieListViewModelModule::class])
interface MovieListComponent {
    fun inject(movieListFragment: MovieListFragment)
}