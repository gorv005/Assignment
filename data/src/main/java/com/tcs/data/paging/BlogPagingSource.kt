package com.tcs.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tcs.data.network.model.BlogDTO
import com.tcs.domain.model.Blog
import com.tcs.common.Constant
import com.tcs.data.network.ApiService
import com.tcs.domain.repository.GetBlogsRepository
import com.tcs.domain.use_cases.GetBlogsUseCase

class BlogPagingSource(private val getBlogsUseCase: GetBlogsUseCase) : PagingSource<Int, Blog>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Blog> {
        return try {
            val currentPage = params.key ?: Constant.STARTING_PAGE_INDEX
            getBlogsUseCase.invoke(page = currentPage, limit = 10).data.let { resp ->
                LoadResult.Page(
                    data = resp ?: listOf(),
                    prevKey = if (currentPage == Constant.STARTING_PAGE_INDEX) null else currentPage - Constant.MAX_PAGE_SIZE,
                    nextKey = if (resp!!.isEmpty()) null else currentPage!! + Constant.MAX_PAGE_SIZE
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Blog>): Int? {
        return state.anchorPosition
    }

}