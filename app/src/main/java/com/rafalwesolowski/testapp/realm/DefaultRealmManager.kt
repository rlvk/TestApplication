package com.rafalwesolowski.testapp.realm

import javax.inject.Inject

import io.realm.Case
import io.realm.Realm
import io.realm.exceptions.RealmException

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class DefaultRealmManager @Inject
constructor(private val mRealm: Realm) : RealmManager {

    override fun saveTask(task: RealmTask) {
        try {
            mRealm.beginTransaction()
            var realmTask = getTask(task.title)
            if (realmTask == null) {
                realmTask = mRealm.createObject(RealmTask::class.java)
            }

            realmTask?.title = task.title

            mRealm.commitTransaction()
        } catch (e: RealmException) {
            mRealm.cancelTransaction()
        }

    }

    override fun deleteTask(task: RealmTask) {
        try {
            mRealm.beginTransaction()

            mRealm.where(RealmTask::class.java)
                    .equalTo(RealmTask.FIELD_NAME, task.title, Case.SENSITIVE)
                    .findAll()
                    .clear()

            mRealm.commitTransaction()
        } catch (e: RealmException) {
            mRealm.cancelTransaction()
        }

    }

    override fun saveTaskWithTitle(taskTitle: String) {
        val realmTask = RealmTask()
        realmTask.title = taskTitle
        saveTask(realmTask)
    }

    override fun getAllTasks(): List<RealmTask> {
        return mRealm.where(RealmTask::class.java).findAllSorted(RealmTask.FIELD_NAME)
    }

    private fun getTask(taskName: String?): RealmTask? {
        return mRealm.where(RealmTask::class.java)
                .equalTo(RealmTask.FIELD_NAME, taskName, Case.SENSITIVE)
                .findFirst()
    }
}
