package com.rafalwesolowski.testapp.mvp.presenters

import android.text.TextUtils

import com.rafalwesolowski.testapp.mvp.BasePresenter
import com.rafalwesolowski.testapp.mvp.views.TodoView
import com.rafalwesolowski.testapp.realm.RealmTask
import com.rafalwesolowski.testapp.realm.RxRealmManager
import com.rafalwesolowski.testapp.utils.SchedulerProvider

import java.util.ArrayList

import javax.inject.Inject

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class TodoPresenter @Inject
constructor(schedulerProvider: SchedulerProvider, private val mRxRealmManager: RxRealmManager) : BasePresenter<TodoView>(schedulerProvider) {

    private val tasksList = ArrayList<RealmTask>()

    override fun onViewCreated(view: TodoView) {
        super.onViewCreated(view)

        view.initAdapter(tasksList)

        refreshRealmObjects()
    }

    fun addItem(itemTitle: String) {
        if (TextUtils.isEmpty(itemTitle)) {
            return
        }

        mRxRealmManager
                .saveTaskWithTitle(itemTitle)
                .doOnSubscribe { view.clearEditText() }
                .doAfterTerminate({ this.refreshRealmObjects() })
                .subscribe()
    }

    fun onLongPress(task: RealmTask) {
        mRxRealmManager
                .deleteTask(task)
                .doAfterTerminate({ this.refreshRealmObjects() })
                .subscribe()
    }

    private fun refreshRealmObjects() {
        mRxRealmManager
                .allTasks
                .doOnSubscribe { tasksList.clear() }
                .subscribe({ tasks ->
                    tasksList.addAll(tasks)
                    view.refreshView()
                })
                { }
    }
}