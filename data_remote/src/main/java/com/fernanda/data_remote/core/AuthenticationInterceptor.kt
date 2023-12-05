package com.fernanda.data_remote.core

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().cacheControl(CacheControl.FORCE_NETWORK)

        addHeader(request)

        return chain.proceed(request.build())
    }

    @Synchronized
    private fun addHeader(builder: Request.Builder) {
        builder.header(HEADER_AUTH_TITLE, HEADER_AUTH_VALUE)
    }
}