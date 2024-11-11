package com.tcs.domain.repository

import com.tcs.common.Resource
import com.tcs.domain.model.Blog

interface GetBlogsRepository {

    suspend fun getBlogs(page: Int, limit: Int): Resource<List<Blog>>

}