package com.fernanda.di

import com.fernanda.data.repository.MatchRepositoryImpl
import com.fernanda.domain.repository.MatchRepository
import org.koin.dsl.module

val dataModule = module {
    single<MatchRepository> { MatchRepositoryImpl(get()) }
}