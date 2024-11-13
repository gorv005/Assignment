package com.tcs.assignment.screens.details.viewmodel

import androidx.compose.runtime.mutableStateOf
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
import com.tcs.assignment.screens.details.BlogDetailsStateHolder
import com.tcs.common.Resource
import com.tcs.domain.use_cases.GetBlogDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class BlogDetailsViewModel @Inject constructor(private val getBlogDetailsUseCase: GetBlogDetailsUseCase): ViewModel(){

    private val _details = MutableStateFlow(BlogDetailsStateHolder())
    val details: StateFlow<BlogDetailsStateHolder> = _details.asStateFlow()

    fun getBlogDetails(id: String) {
        getBlogDetailsUseCase.invoke(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _details.value = BlogDetailsStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    _details.value = BlogDetailsStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    _details.value = BlogDetailsStateHolder(error = it.message.toString())
                }
            }


        }.launchIn(viewModelScope)
    }

}