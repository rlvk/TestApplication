package com.rafalwesolowski.testapp.datamodel

import com.google.gson.annotations.SerializedName

/**
 * Created by rafalwesolowski on 20/01/2018.
 */
data class User(@SerializedName("login") val login: String? = null,
                @SerializedName("score") val score: String? = null
)