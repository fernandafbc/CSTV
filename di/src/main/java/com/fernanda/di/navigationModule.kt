package com.fernanda.di

import com.fernanda.matches_list.MatchesListNavigation
import com.fernanda.navigation.core.NavigationManager
import com.fernanda.navigation.navigation.MatchesListNavigationImpl
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

    single<MatchesListNavigation> { MatchesListNavigationImpl(get()) }
}