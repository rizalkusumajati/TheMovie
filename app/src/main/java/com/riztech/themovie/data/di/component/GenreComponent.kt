package com.riztech.themovie.data.di.component

import com.riztech.themovie.data.di.module.GenreViewModelModule
import com.riztech.themovie.data.di.scope.GenreScope
import com.riztech.themovie.presentation.GenreFragment
import dagger.Component

@GenreScope
@Component(dependencies = [CoreComponent::class],
    modules = [GenreViewModelModule::class])
interface GenreComponent {
    fun inject(genreFragment: GenreFragment)
}