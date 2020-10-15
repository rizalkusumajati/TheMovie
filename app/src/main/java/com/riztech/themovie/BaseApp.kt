package com.riztech.themovie

import android.app.Application
import com.riztech.themovie.data.di.component.CoreComponent
import com.riztech.themovie.data.di.component.CoreComponentProvider
import com.riztech.themovie.data.di.component.DaggerCoreComponent
import com.riztech.themovie.data.di.module.DbModule
import com.riztech.themovie.data.di.module.NetworkModule
import com.riztech.themovie.data.di.module.SharePrefModule

class BaseApp : Application(), CoreComponentProvider {
    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        coreComponent = DaggerCoreComponent
            .builder()
            .application(this)
            .sharedPrefModule(
                SharePrefModule()
            )
            .networkModule(
                NetworkModule()
            )
            .dbModule(
                DbModule()
            ).build()

    }

    override fun provideCoreComponent(): CoreComponent {
        return coreComponent
    }
}