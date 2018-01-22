package com.rafalwesolowski.testapp.test_utils

import com.rafalwesolowski.testapp.utils.SchedulerProvider

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class TestSchedulerProvider : SchedulerProvider {

    override fun getBackgroundScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun getMainThreadScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}
