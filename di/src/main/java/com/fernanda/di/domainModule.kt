package com.fernanda.di

import com.fernanda.domain.usecase.GetMatchesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {

    single {
        CoroutineScope(Dispatchers.IO)
    }

    factory {
        GetMatchesListUseCase(
            scope = get(),
            repository = get()
        )
    }
}