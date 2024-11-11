package com.tcs.domain.repository

import com.gaur.domain.model.Blog

interface GetBlogDetailsRepo {

    suspend fun getBlogDetails(id: String): Blog

}