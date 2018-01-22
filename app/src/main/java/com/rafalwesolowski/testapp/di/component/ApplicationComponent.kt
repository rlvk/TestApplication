package com.rafalwesolowski.testapp.di.component

import com.rafalwesolowski.testapp.TaskApplication
import com.rafalwesolowski.testapp.di.modules.ApplicationModule
import com.rafalwesolowski.testapp.di.modules.NetworkModule
import com.rafalwesolowski.testapp.di.modules.RealmModule
import com.rafalwesolowski.testapp.ui.GithubFragment
import com.rafalwesolowski.testapp.ui.LoginFragment
import com.rafalwesolowski.testapp.ui.TodoFragment

import javax.inject.Singleton

import dagger.Component

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, RealmModule::class))
interface ApplicationComponent {
    fun inject(application: TaskApplication)
    fun inject(githubFragment: GithubFragment)
    fun inject(todoFragment: TodoFragment)
    fun inject(loginFragment: LoginFragment)
}