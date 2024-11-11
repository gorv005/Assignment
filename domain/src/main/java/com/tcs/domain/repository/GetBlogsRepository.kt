package com.tcs.domain.repository

import com.gaur.domain.model.Blog
import com.tcs.common.Resource

interface GetBlogsRepository {

    suspend fun getBlogs(page: Int, limit: Int): Resource<List<Blog>>

}