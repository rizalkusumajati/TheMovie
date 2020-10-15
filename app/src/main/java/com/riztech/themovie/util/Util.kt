package com.riztech.themovie.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.riztech.themovie.data.di.component.CoreComponentProvider

fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")

fun Fragment.coreComponent() = requireActivity().coreComponent()