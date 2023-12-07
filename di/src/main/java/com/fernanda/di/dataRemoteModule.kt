package com.fernanda.di

import com.fernanda.data.MatchDataSource
import com.fernanda.data_remote.core.BASE_URL
import com.fernanda.data_remote.core.Retrofit
import com.fernanda.data_remote.datasource.MatchDataSourceImpl
import com.fernanda.data_remote.service.WebService
import org.koin.dsl.module

val dataRemoteModule = module {
    single<WebService> {
        Retrofit(
            baseUrl = BASE_URL
        )
    }

    factory<MatchDataSource> { MatchDataSourceImpl(get()) }
}