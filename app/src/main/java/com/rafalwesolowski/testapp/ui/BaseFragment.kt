package com.rafalwesolowski.testapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

open class BaseFragment : Fragment() {

    private var mUnbinder: Unbinder? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUnbinder = ButterKnife.bind(this, view!!)
    }

    override fun onDestroyView() {

        mUnbinder?.unbind()

        super.onDestroyView()
    }
}