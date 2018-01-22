package com.rafalwesolowski.testapp.realm

import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by rafalwesolowski on 21/01/2018.
 */
@RealmClass
open class RealmTask : RealmObject() {

    open var title: String? = null

    companion object {

        val FIELD_NAME = "title"
    }
}
