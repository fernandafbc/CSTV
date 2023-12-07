package com.fernanda.di

import com.fernanda.domain.core.ThreadContextProvider
import com.fernanda.domain.usecase.GetMatchesListUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    single {
        ThreadContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetMatchesListUseCase(
            scope = scope,
            repository = get()
        )
    }
}