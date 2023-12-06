package com.fernanda.cstv

import android.app.Application
import com.fernanda.di.dataModule
import com.fernanda.di.dataRemoteModule
import com.fernanda.di.domainModule
import com.fernanda.di.featureModule
import com.fernanda.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataModule,
                dataRemoteModule,
                domainModule,
                featureModule,
                navigationModule
            ).androidContext(applicationContext)
        }
    }
}