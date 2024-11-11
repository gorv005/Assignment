package com.tcs.data.repository


import com.tcs.data.network.utils.SafeApiRequest
import com.tcs.data.mappers.toDomain
import com.tcs.data.network.ApiService
import com.tcs.domain.model.Blog
import com.tcs.domain.model.Owner
import com.tcs.domain.repository.GetBlogDetailsRepo
import javax.inject.Inject

class GetBlogDetailsRepoImpl @Inject constructor(private val apiService: ApiService) :
    GetBlogDetailsRepo, SafeApiRequest() {
    override suspend fun getBlogDetails(id: String): Blog {
        val response = safeApiRequest { apiService.getBlogDetails(id = id) }

        val blog = Blog(
            id = response.id?:"",
            image = response.image ?: "",
            likes = response.likes ?: 0,
            owner = response.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = response.publishDate ?: "",
            tags = response.tags ?: emptyList(),
            text = response.text ?: ""
        )

        return blog
    }
}