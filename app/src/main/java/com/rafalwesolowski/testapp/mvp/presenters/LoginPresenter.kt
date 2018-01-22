package com.rafalwesolowski.testapp.mvp.presenters

import com.rafalwesolowski.testapp.mvp.BasePresenter
import com.rafalwesolowski.testapp.mvp.views.LoginView
import com.rafalwesolowski.testapp.utils.SchedulerProvider
import com.rafalwesolowski.testapp.utils.ValidationUtil

import javax.inject.Inject

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

class LoginPresenter @Inject
constructor(schedulerProvider: SchedulerProvider) : BasePresenter<LoginView>(schedulerProvider) {

    fun onEmailChanged(email: String) {
        val isValidEmail = ValidationUtil.isValidEmail(email)
        view.validateEmailInputLayout(isValidEmail)
        view.validateLoginButton(isValidEmail)
    }

    fun onPasswordChanged(password: String) {
        val isValidPassword = ValidationUtil.isValidPassword(password)
        view.validatePasswordInputLayout(isValidPassword)
        view.validateLoginButton(isValidPassword)
    }

    fun onForgotPasswordClick() {
        //TODO N/A
    }

    fun onLoginClicked() {
        //TODO N/A
    }

    fun onWorkClicked() {
        //TODO N/A
    }
}