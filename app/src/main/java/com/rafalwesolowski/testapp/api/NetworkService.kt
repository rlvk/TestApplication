package com.rafalwesolowski.testapp.api

import com.rafalwesolowski.testapp.datamodel.SearchResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rafalwesolowski on 20/01/2018.
 */

interface NetworkService {
    @GET("search/users")
    fun getUsers(@Query("q") keyword: String): Single<SearchResponse>
}
