package com.rafalwesolowski.testapp.utils

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

object RxUtils {

    fun <T> applySchedulers(schedulerProvider: SchedulerProvider): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                    .subscribeOn(schedulerProvider.getBackgroundScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
        }
    }

    fun <T> applySchedulersSingle(schedulerProvider: SchedulerProvider): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream
                    .subscribeOn(schedulerProvider.getBackgroundScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
        }
    }

    fun applySchedulersCompletable(schedulerProvider: SchedulerProvider): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream
                    .subscribeOn(schedulerProvider.getBackgroundScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
        }
    }
}