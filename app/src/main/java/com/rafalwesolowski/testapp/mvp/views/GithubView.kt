package com.rafalwesolowski.testapp.mvp.views

import com.rafalwesolowski.testapp.datamodel.User
import com.rafalwesolowski.testapp.mvp.BaseView

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

interface GithubView : BaseView {
    fun showProgress()
    fun hideProgress()
    fun showEmptyView()
    fun initAdapter(users: List<User>)

    fun showLoadedUsers()
    fun showErrorMessage(errorMessage: String)
}