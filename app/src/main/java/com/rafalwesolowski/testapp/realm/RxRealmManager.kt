package com.rafalwesolowski.testapp.realm

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class RxRealmManager(private val mRealmManager: RealmManager) {

    val allTasks: Single<List<RealmTask>>
        get() = Single.fromCallable({ mRealmManager.getAllTasks() })

    fun saveTask(task: RealmTask): Completable {
        return Completable.fromAction { mRealmManager.saveTask(task) }
    }

    fun deleteTask(task: RealmTask): Completable {
        return Completable.fromAction { mRealmManager.deleteTask(task) }
    }

    fun saveTaskWithTitle(taskTitle: String): Completable {
        return Completable.fromAction { mRealmManager.saveTaskWithTitle(taskTitle) }
    }
}