package com.tcs.assignment.screens.details

import com.tcs.domain.model.Blog

data class BlogDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: Blog? = null,
    val error: String = ""
)