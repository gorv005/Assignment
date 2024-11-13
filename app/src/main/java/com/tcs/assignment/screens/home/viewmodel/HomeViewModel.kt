package com.tcs.assignment.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tcs.common.Constant.MAX_PAGE_SIZE
import com.tcs.data.paging.BlogPagingSource
import com.tcs.domain.use_cases.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

@HiltViewModel
class HomeViewModel @Inject constructor(private val getBlogsUseCase: GetBlogsUseCase) :
    ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = MAX_PAGE_SIZE, prefetchDistance = 5),
        pagingSourceFactory = { BlogPagingSource(getBlogsUseCase) }
    ).flow.cachedIn(viewModelScope)

}