package com.rafalwesolowski.testapp.mvp.presenters

import com.rafalwesolowski.testapp.api.NetworkService
import com.rafalwesolowski.testapp.datamodel.SearchResponse
import com.rafalwesolowski.testapp.datamodel.User
import com.rafalwesolowski.testapp.mvp.BasePresenter
import com.rafalwesolowski.testapp.mvp.views.GithubView
import com.rafalwesolowski.testapp.utils.SchedulerProvider

import java.util.ArrayList
import java.util.concurrent.TimeUnit

import javax.inject.Inject

import io.reactivex.Observable

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

class GithubPresenter @Inject
constructor(schedulerProvider: SchedulerProvider, private val mNetworkService: NetworkService) : BasePresenter<GithubView>(schedulerProvider) {
    private val mUsersList = ArrayList<User>()

    override fun onViewCreated(view: GithubView) {
        super.onViewCreated(view)

        getView().initAdapter(mUsersList)
    }

    fun observeOnSearchView(searchObservable: Observable<String>) {
        addDisposable(searchObservable
                .debounce(SEARCH_THROTTLE.toLong(), TimeUnit.MILLISECONDS)
                .compose(handleNetworkCall())
                .filter { text ->
                    var isNotEmpty = true
                    if (text.isEmpty()) {
                        view.showEmptyView()
                        isNotEmpty = false
                    }

                    isNotEmpty
                }
                .distinctUntilChanged()
                .subscribe({ this.searchUser(it) }))
    }

    fun searchUser(query: String) {
        addDisposable(mNetworkService.getUsers(query)
                .compose<SearchResponse>(handleNetworkCallSingle<SearchResponse>())
                .doOnSubscribe {
                    mUsersList.clear()
                    view.showProgress()
                }
                .doAfterTerminate { view.hideProgress() }
                .subscribe({ searchResponse ->
                    mUsersList.addAll(searchResponse.users!!)
                    if (mUsersList.isEmpty()) {
                        view.showEmptyView()
                    } else {
                        view.showLoadedUsers()
                    }
                }
                ) { throwable -> view.showErrorMessage(throwable.message!!) })
    }

    companion object {

        private val SEARCH_THROTTLE = 200
    }
}
