package com.rafalwesolowski.testapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.rafalwesolowski.testapp.realm.RealmTask

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class TodoAdapter(private val mTasks: List<RealmTask>?) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val mItemSelectedSubject = PublishSubject.create<RealmTask>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
                android.R.layout.simple_list_item_1, viewGroup, false)

        val todoViewHolder = TodoViewHolder(view)

        view.setOnLongClickListener {
            mItemSelectedSubject.onNext(mTasks!![todoViewHolder.adapterPosition])
            true
        }

        return todoViewHolder
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        val task = mTasks!![position]

        holder.mPrimaryText.text = task.title
    }

    override fun getItemCount(): Int {
        return mTasks?.size ?: 0
    }

    fun listen(): Observable<RealmTask> {
        return mItemSelectedSubject
    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mPrimaryText: TextView = view.findViewById(android.R.id.text1)

    }
}