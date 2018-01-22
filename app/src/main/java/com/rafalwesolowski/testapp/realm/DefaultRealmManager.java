package com.rafalwesolowski.testapp.realm;

import java.util.List;

import javax.inject.Inject;

import io.realm.Case;
import io.realm.Realm;
import io.realm.exceptions.RealmException;

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

public class DefaultRealmManager implements RealmManager {

    private final Realm mRealm;

    @Inject
    public DefaultRealmManager(Realm realm) {
        mRealm = realm;
    }

    @Override
    public void saveTask(RealmTask task) {
        try {
            if (task != null) {
                mRealm.beginTransaction();
                RealmTask realmTask = getTask(task.getTitle());
                if (realmTask == null) {
                    realmTask = mRealm.createObject(RealmTask.class);
                }

                if (realmTask != null) {
                    realmTask.setTitle(task.getTitle());
                }

                mRealm.commitTransaction();
            }
        } catch (RealmException e) {
            mRealm.cancelTransaction();
        }
    }

    @Override
    public void deleteTask(RealmTask task) {
        try {
            if (task != null) {
                mRealm.beginTransaction();

                mRealm.where(RealmTask.class)
                        .equalTo(RealmTask.FIELD_NAME, task.getTitle(), Case.SENSITIVE)
                        .findAll()
                        .clear();

                mRealm.commitTransaction();
            }
        } catch (RealmException e) {
            mRealm.cancelTransaction();
        }
    }

    @Override
    public void saveTaskWithTitle(String taskTitle) {
        RealmTask realmTask = new RealmTask(taskTitle);
        saveTask(realmTask);
    }

    @Override
    public List<RealmTask> getAllTasks() {
        return mRealm.where(RealmTask.class).findAllSorted(RealmTask.FIELD_NAME);
    }

    private RealmTask getTask(String taskName) {
        return mRealm.where(RealmTask.class)
                .equalTo(RealmTask.FIELD_NAME, taskName, Case.SENSITIVE)
                .findFirst();
    }
}
