package com.rafalwesolowski.testapp.mvp

import com.rafalwesolowski.testapp.utils.RxUtils
import com.rafalwesolowski.testapp.utils.SchedulerProvider

import java.lang.ref.WeakReference

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

open class BasePresenter<T : BaseView>(private val mSchedulerProvider: SchedulerProvider) {

    private var mViewRef: WeakReference<T>? = null
    private var mCompositeDisposable: CompositeDisposable? = null

    protected val view: T
        get() = if (!isViewAttached) {
            throw ViewNotAttachedException()
        } else {
            mViewRef?.get()!!
        }

    private val isViewAttached: Boolean
        get() = mViewRef?.get() != null

    open fun onViewCreated(view: T) {
        mCompositeDisposable = CompositeDisposable()
        mViewRef = WeakReference(view)
    }

    fun onViewDestroyed() {
        mCompositeDisposable?.dispose()
        mCompositeDisposable = null

        mViewRef?.clear()
    }

    protected fun <U> handleNetworkCall(): ObservableTransformer<U, U> {
        return ObservableTransformer { upstream ->
            upstream
                    .compose(RxUtils.applySchedulers<U>(mSchedulerProvider))
        }
    }

    protected fun <U> handleNetworkCallSingle(): SingleTransformer<U, U> {
        return SingleTransformer { upstream ->
            upstream
                    .compose(RxUtils.applySchedulersSingle(mSchedulerProvider))
        }
    }

    protected fun handleNetworkCallCompletable(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream
                    .compose(RxUtils.applySchedulersCompletable(mSchedulerProvider))
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        mCompositeDisposable!!.add(disposable)
    }

    private class ViewNotAttachedException internal constructor() : IllegalStateException("Trying to access a view that is not attached")
}