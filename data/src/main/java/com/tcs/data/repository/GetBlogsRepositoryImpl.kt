package com.tcs.data.repository

import com.gaur.data.network.utils.SafeApiRequest
import com.gaur.domain.model.Blog
import com.tcs.common.Resource
import com.tcs.data.mappers.toDomain
import com.tcs.data.network.ApiService
import com.tcs.domain.repository.GetBlogsRepository
import javax.inject.Inject

class GetBlogsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetBlogsRepository {


    override suspend fun getBlogs(page: Int, limit: Int): Resource<List<Blog>> {
        return try {
            val response = apiService.getBlogs(page = page, limit = limit)
            if (response.isSuccessful) {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)

            } else {
                Resource.Error(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}

