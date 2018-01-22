package com.rafalwesolowski.testapp.datamodel

import com.google.gson.annotations.SerializedName

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

data class SearchResponse(
        @SerializedName("total_count") val totalCount: Int = 0,
        @SerializedName("incomplete_results") val isIncompleteResults: Boolean = false,
        @SerializedName("items") val users: List<User>? = null,
        @SerializedName("message") val message: String? = null
)
