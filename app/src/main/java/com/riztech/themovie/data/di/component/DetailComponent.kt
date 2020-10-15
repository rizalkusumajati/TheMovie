package com.riztech.themovie.data.di.component

import com.riztech.themovie.data.di.module.DetailViewModelModule
import com.riztech.themovie.data.di.scope.DetailScope
import com.riztech.themovie.presentation.DetailFragment
import dagger.Component

@DetailScope
@Component(modules = [DetailViewModelModule::class])
interface DetailComponent {
    fun inject(detailFragment: DetailFragment)
}