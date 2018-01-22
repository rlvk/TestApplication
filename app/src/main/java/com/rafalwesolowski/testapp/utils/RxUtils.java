package com.rafalwesolowski.testapp.utils;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

public class RxUtils {

    public static <T> ObservableTransformer<T, T> applySchedulers(SchedulerProvider schedulerProvider) {
        return upstream -> upstream.subscribeOn(schedulerProvider.getBackgroundScheduler())
                .observeOn(schedulerProvider.getMainThreadScheduler());
    }

    public static <T> SingleTransformer<T, T> applySchedulersSingle(SchedulerProvider schedulerProvider) {
        return upstream -> upstream.subscribeOn(schedulerProvider.getBackgroundScheduler())
                .observeOn(schedulerProvider.getMainThreadScheduler());
    }

    public static CompletableTransformer applySchedulersCompletable(SchedulerProvider schedulerProvider) {
        return upstream -> upstream.subscribeOn(schedulerProvider.getBackgroundScheduler())
                .observeOn(schedulerProvider.getMainThreadScheduler());
    }
}
