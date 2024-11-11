package com.tcs.data.network

import com.tcs.data.network.model.BlogDTO
import com.tcs.data.network.model.BlogsDTO
import com.tcs.common.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("post")
    suspend fun getBlogs(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BlogsDTO>

    @GET("post/{id}")
    suspend fun getBlogDetails(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Path("id") id: String
    ): Response<BlogDTO>
}