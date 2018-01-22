package com.rafalwesolowski.testapp.mvp.views

import com.rafalwesolowski.testapp.mvp.BaseView

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

interface LoginView : BaseView {
    fun validateEmailInputLayout(valid: Boolean)
    fun validatePasswordInputLayout(valid: Boolean)
    fun validateLoginButton(valid: Boolean)
}
