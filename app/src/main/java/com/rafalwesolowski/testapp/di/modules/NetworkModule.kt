package com.rafalwesolowski.testapp.di.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.rafalwesolowski.testapp.BuildConfig
import com.rafalwesolowski.testapp.api.NetworkService
import com.rafalwesolowski.testapp.utils.SchedulerProvider

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rafalwesolowski on 17/01/2018.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesSchedulerProvider(): SchedulerProvider {
        return object : SchedulerProvider {
            override fun getBackgroundScheduler(): Scheduler {
                return Schedulers.io()
            }

            override fun getMainThreadScheduler(): Scheduler {
                return AndroidSchedulers.mainThread()
            }
        }
    }

    @Provides
    @Singleton
    fun providesRetrofitCall(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}
