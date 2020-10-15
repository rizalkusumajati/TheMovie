package com.riztech.themovie.data.di.component

import com.riztech.themovie.data.di.module.HomeViewModelModule
import com.riztech.themovie.data.di.scope.HomeScope
import com.riztech.themovie.presentation.HomeFragment
import dagger.Component

@HomeScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [HomeViewModelModule::class])
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
}