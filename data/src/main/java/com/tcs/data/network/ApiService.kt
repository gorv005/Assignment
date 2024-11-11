package com.tcs.data.network

import com.gaur.data.network.model.BlogsDTO
import com.tcs.common.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("post")
    suspend fun getBlogs(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BlogsDTO>
}