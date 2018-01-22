package com.rafalwesolowski.testapp.utils

import android.text.TextUtils

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

object ValidationUtil {

    private val MINIMUM_PASSWORD_LENGTH = 6

    fun isValidEmail(email: CharSequence): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: CharSequence): Boolean {
        return !TextUtils.isEmpty(password) && password.length > MINIMUM_PASSWORD_LENGTH
    }
}
