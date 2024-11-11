package com.tcs.domain.repository

import com.tcs.domain.model.Blog


interface GetBlogDetailsRepo {

    suspend fun getBlogDetails(id: String): Blog

}