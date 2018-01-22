package com.rafalwesolowski.testapp.di.modules

import com.rafalwesolowski.testapp.realm.DefaultRealmManager
import com.rafalwesolowski.testapp.realm.RealmManager
import com.rafalwesolowski.testapp.realm.RxRealmManager

import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

@Module
class RealmModule {

    @Provides
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    fun provideRealmManager(realm: Realm): RealmManager {
        return DefaultRealmManager(realm)
    }

    @Provides
    fun provideRxRealmManager(realmManager: RealmManager): RxRealmManager {
        return RxRealmManager(realmManager)
    }
}