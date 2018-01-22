package com.rafalwesolowski.testapp.mvp.views

import com.rafalwesolowski.testapp.mvp.BaseView
import com.rafalwesolowski.testapp.realm.RealmTask

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

interface TodoView : BaseView {
    fun initAdapter(tasks: List<RealmTask>)

    fun clearEditText()
    fun refreshView()
}