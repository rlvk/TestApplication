package com.rafalwesolowski.testapp.ui

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.rafalwesolowski.testapp.R
import com.rafalwesolowski.testapp.TaskApplication
import com.rafalwesolowski.testapp.mvp.presenters.LoginPresenter
import com.rafalwesolowski.testapp.mvp.views.LoginView

import javax.inject.Inject

import butterknife.BindView
import butterknife.OnClick
import butterknife.OnTextChanged

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

class LoginFragment : BaseFragment(), LoginView {

    @Inject lateinit var mLoginPresenter: LoginPresenter

    @BindView(R.id.login_button) lateinit var mLoginButton: Button
    @BindView(R.id.input_layout_email_address) lateinit var mEmailInputLayout: TextInputLayout
    @BindView(R.id.input_layout_password) lateinit var mPasswordInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskApplication.instance!!.applicationComponent!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_login_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLoginPresenter.onViewCreated(this)
    }

    override fun onDestroyView() {
        mLoginPresenter.onViewDestroyed()
        super.onDestroyView()
    }

    @OnClick(R.id.forgot_password_text_button)
    internal fun forgotPasswordButtonClicked() {
        mLoginPresenter.onForgotPasswordClick()
    }

    @OnClick(R.id.login_button)
    internal fun loginButtonClicked() {
        mLoginPresenter.onLoginClicked()
    }

    @OnClick(R.id.work_button)
    internal fun workButtonClicked() {
        mLoginPresenter.onWorkClicked()
    }

    @OnTextChanged(R.id.input_email_address_edit_text)
    internal fun onEmailChanged(string: CharSequence) {
        mLoginPresenter.onEmailChanged(string.toString())
    }

    @OnTextChanged(R.id.input_password_edit_text)
    internal fun onPasswordChanged(string: CharSequence) {
        mLoginPresenter.onPasswordChanged(string.toString())
    }

    override fun validateEmailInputLayout(valid: Boolean) {
        if (valid) {
            mEmailInputLayout.isErrorEnabled = false
            mEmailInputLayout.error = null
        } else {
            mEmailInputLayout.isErrorEnabled = true
            mEmailInputLayout.error = getString(R.string.invalid_email)
        }
    }

    override fun validatePasswordInputLayout(valid: Boolean) {
        if (valid) {
            mPasswordInputLayout.isErrorEnabled = false
            mPasswordInputLayout.error = null
        } else {
            mPasswordInputLayout.isErrorEnabled = true
            mPasswordInputLayout.error = getString(R.string.invalid_password)
        }
    }

    override fun validateLoginButton(valid: Boolean) {
        mLoginButton.isEnabled = valid
    }

    companion object {

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
