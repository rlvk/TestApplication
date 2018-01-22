package com.rafalwesolowski.testapp.di.modules

import com.rafalwesolowski.testapp.TaskApplication

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by rafalwesolowski on 17/01/2018.
 */

@Module
class ApplicationModule(private val mApplication: TaskApplication) {

    @Provides
    @Singleton
    fun providesApplication() = mApplication

    @Provides
    @Singleton
    fun providesApplicationContext() = mApplication.applicationContext!!
}
