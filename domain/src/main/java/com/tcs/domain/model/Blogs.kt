package com.tcs.domain.model

data class Blogs(
    val `data`: List<Blog>,
    val limit: Int,
    val page: Int,
    val total: Int
)
