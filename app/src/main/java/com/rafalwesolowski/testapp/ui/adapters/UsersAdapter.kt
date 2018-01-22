package com.rafalwesolowski.testapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.rafalwesolowski.testapp.datamodel.User

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class UsersAdapter(private val mUsers: List<User>?) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private val mItemSelectedSubject = PublishSubject.create<User>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
                android.R.layout.simple_list_item_2, viewGroup, false)

        val usersViewHolder = UsersViewHolder(view)

        view.setOnClickListener { mItemSelectedSubject.onNext(mUsers!![usersViewHolder.adapterPosition]) }

        return usersViewHolder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val (login, score) = mUsers!![position]

        holder.mPrimaryText.text = login
        holder.mSecondaryText.text = score
    }

    override fun getItemCount(): Int {
        return mUsers?.size ?: 0
    }

    internal fun listen(): Observable<User> {
        return mItemSelectedSubject
    }

    inner class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mPrimaryText: TextView = view.findViewById(android.R.id.text1)
        val mSecondaryText: TextView = view.findViewById(android.R.id.text2)

    }
}