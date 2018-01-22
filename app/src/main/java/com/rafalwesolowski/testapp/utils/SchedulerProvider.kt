package com.rafalwesolowski.testapp.utils

import io.reactivex.Scheduler

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

interface SchedulerProvider {
    fun getBackgroundScheduler(): Scheduler
    fun getMainThreadScheduler(): Scheduler
}
