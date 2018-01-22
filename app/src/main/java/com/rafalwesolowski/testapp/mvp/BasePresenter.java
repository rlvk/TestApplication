package com.rafalwesolowski.testapp.mvp;

import com.rafalwesolowski.testapp.utils.RxUtils;
import com.rafalwesolowski.testapp.utils.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

public class BasePresenter<T extends BaseView> {

    private WeakReference<T> mViewRef;
    private CompositeDisposable mCompositeDisposable;

    private final SchedulerProvider mSchedulerProvider;

    public BasePresenter(SchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    public void onViewCreated(T view) {
        mCompositeDisposable = new CompositeDisposable();
        mViewRef = new WeakReference<>(view);
    }

    public void onViewDestroyed() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }

        if (mViewRef != null) {
            mViewRef.clear();
        }
    }

    protected <U> ObservableTransformer<U, U> handleNetworkCall() {
        return upstream -> upstream
                .compose(RxUtils.applySchedulers(mSchedulerProvider));
    }

    protected <U> SingleTransformer<U, U> handleNetworkCallSingle() {
        return upstream -> upstream
                .compose(RxUtils.applySchedulersSingle(mSchedulerProvider));
    }

    protected CompletableTransformer handleNetworkCallCompletable() {
        return upstream -> upstream
                .compose(RxUtils.applySchedulersCompletable(mSchedulerProvider));
    }

    protected final void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected final T getView() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedException();
        } else {
            return mViewRef.get();
        }
    }

    private boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    private static class ViewNotAttachedException extends IllegalStateException {
        ViewNotAttachedException() {
            super("Trying to access a view that is not attached");
        }
    }
}
