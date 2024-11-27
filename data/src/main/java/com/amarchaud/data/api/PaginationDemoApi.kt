package com.amarchaud.data.api

import com.amarchaud.data.models.ResultsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PaginationDemoApi {
    @GET("/api/1.3/")
    suspend fun getRandomUsers(
        @Query("seed") seed: String = "PaginationDemo",
        @Query("results") results: Int = 20,
        @Query("page") page: Int
    ): Response<ResultsDataModel>
}