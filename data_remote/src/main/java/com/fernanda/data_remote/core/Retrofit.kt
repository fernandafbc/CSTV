package com.fernanda.data_remote.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object Retrofit {
    inline operator fun <reified T> invoke(
        baseUrl: String,
    ): T = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(OkHttp.invoke())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create()

    object OkHttp {
        operator fun invoke(): OkHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(AuthenticationInterceptor())
            connectTimeout(timeout = TIMEOUT_DURATION, TimeUnit.SECONDS)
            readTimeout(timeout = TIMEOUT_DURATION, TimeUnit.SECONDS)
            writeTimeout(timeout = TIMEOUT_DURATION, TimeUnit.SECONDS)
        }.build()
    }
}