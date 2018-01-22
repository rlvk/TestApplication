package com.rafalwesolowski.testapp.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by rafalwesolowski on 21/01/2018.
 */
@RealmClass
public class RealmTask extends RealmObject {

    public static final String FIELD_NAME = "title";

    private String title;

    public RealmTask() { }

    public RealmTask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
