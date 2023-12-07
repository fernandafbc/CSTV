package com.fernanda.di

import com.fernanda.navigation.core.NavigationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val navigationModule = module {
    single {
        NavigationManager(
            CoroutineScope(
                context = SupervisorJob() + Dispatchers.Main
            )
        )
    }
}