package com.tcs.domain.use_cases

import com.tcs.common.Resource
import com.tcs.domain.model.Blog
import com.tcs.domain.repository.GetBlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogsUseCase  @Inject constructor(private val getBlogsRepository: GetBlogsRepository){


     suspend operator fun invoke(page: Int, limit: Int) : Resource<List<Blog>>{

            return getBlogsRepository.getBlogs(page,limit)
    }


}