package com.rafalwesolowski.testapp.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.rafalwesolowski.testapp.R
import com.rafalwesolowski.testapp.TaskApplication
import com.rafalwesolowski.testapp.mvp.presenters.TodoPresenter
import com.rafalwesolowski.testapp.mvp.views.TodoView
import com.rafalwesolowski.testapp.realm.RealmTask
import com.rafalwesolowski.testapp.ui.adapters.TodoAdapter

import javax.inject.Inject

import butterknife.BindView
import butterknife.OnClick
import io.reactivex.disposables.Disposables

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class TodoFragment : BaseFragment(), TodoView {

    @BindView(R.id.to_do_recycler_view) lateinit var mRecyclerView: RecyclerView
    @BindView(R.id.add_item_edit_text) lateinit var mAddItemEditText: EditText

    @Inject lateinit var mTodoPresenter: TodoPresenter

    private var onLongPressDisposable = Disposables.disposed()
    private var mAdapter: TodoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskApplication.instance!!.applicationComponent!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTodoPresenter.onViewCreated(this)
    }

    override fun onDestroyView() {
        mTodoPresenter.onViewDestroyed()
        onLongPressDisposable.dispose()
        super.onDestroyView()
    }

    @OnClick(R.id.add_item_fab)
    internal fun addItemClick() {
        mTodoPresenter.addItem(mAddItemEditText.text.toString().trim { it <= ' ' })
    }

    override fun initAdapter(tasks: List<RealmTask>) {
        mAdapter = TodoAdapter(tasks)

        onLongPressDisposable = mAdapter!!.listen().subscribe({ mTodoPresenter.onLongPress(it) })

        mRecyclerView.adapter = mAdapter
    }

    override fun clearEditText() {
        mAddItemEditText.text.clear()
    }

    override fun refreshView() {
        mAdapter!!.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): TodoFragment {
            return TodoFragment()
        }
    }
}