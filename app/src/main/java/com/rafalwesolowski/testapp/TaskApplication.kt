package com.rafalwesolowski.testapp

import android.app.Application

import com.rafalwesolowski.testapp.di.component.ApplicationComponent
import com.rafalwesolowski.testapp.di.component.DaggerApplicationComponent
import com.rafalwesolowski.testapp.di.modules.ApplicationModule

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

class TaskApplication : Application() {
    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        val realmConfiguration = RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        applicationComponent = initDagger()
        applicationComponent!!.inject(this)

    }

    private fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(instance!!))
                .build()
    }

    companion object {

        var instance: TaskApplication? = null
            private set
    }
}