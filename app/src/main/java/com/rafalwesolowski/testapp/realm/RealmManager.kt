package com.rafalwesolowski.testapp.realm

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

interface RealmManager {
    fun getAllTasks(): List<RealmTask>
    fun saveTask(task: RealmTask)
    fun deleteTask(task: RealmTask)
    fun saveTaskWithTitle(taskTitle: String)
}