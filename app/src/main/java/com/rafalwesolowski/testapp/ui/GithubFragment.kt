package com.rafalwesolowski.testapp.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.rafalwesolowski.testapp.R
import com.rafalwesolowski.testapp.TaskApplication
import com.rafalwesolowski.testapp.datamodel.User
import com.rafalwesolowski.testapp.mvp.presenters.GithubPresenter
import com.rafalwesolowski.testapp.mvp.views.GithubView
import com.rafalwesolowski.testapp.ui.adapters.UsersAdapter
import com.rafalwesolowski.testapp.utils.RxSearch

import javax.inject.Inject

import butterknife.BindView

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

class GithubFragment : BaseFragment(), GithubView {

    @Inject lateinit var mGithubPresenter: GithubPresenter

    @BindView(R.id.fragment_github_recycler_view) lateinit var mRecyclerView: RecyclerView
    @BindView(R.id.progress_bar) lateinit var mProgressBar: ProgressBar
    @BindView(R.id.no_results_available) lateinit var mNoResultsAvailable: TextView

    private var mUsersAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskApplication.instance!!.applicationComponent!!.inject(this)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_github_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mGithubPresenter.onViewCreated(this)
    }

    override fun onDestroyView() {
        mGithubPresenter.onViewDestroyed()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater!!.inflate(R.menu.menu_main, menu)
        val search = menu!!.findItem(R.id.search)
        val searchView = search.actionView as SearchView

        mGithubPresenter.observeOnSearchView(RxSearch.fromSearchView(searchView))
    }

    override fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
        mNoResultsAvailable.visibility = View.GONE
    }

    override fun hideProgress() {
        mProgressBar.visibility = View.GONE
        mRecyclerView.visibility = View.VISIBLE
    }

    override fun showEmptyView() {
        mNoResultsAvailable.visibility = View.VISIBLE
        mRecyclerView.visibility = View.GONE
    }

    override fun initAdapter(users: List<User>) {
        mUsersAdapter = UsersAdapter(users)
        mRecyclerView.adapter = mUsersAdapter
    }

    override fun showLoadedUsers() {
        mUsersAdapter!!.notifyDataSetChanged()
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance(): GithubFragment {
            return GithubFragment()
        }
    }
}