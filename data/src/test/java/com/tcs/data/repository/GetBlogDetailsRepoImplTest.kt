package com.tcs.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.tcs.common.Resource
import com.tcs.data.network.ApiService
import com.tcs.data.network.model.BlogDTO
import com.tcs.data.network.model.BlogsDTO
import com.tcs.data.network.model.OwnerDTO
import com.tcs.domain.model.Blog
import com.tcs.domain.model.Blogs
import com.tcs.domain.model.Owner
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class GetBlogDetailsRepoImplTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getBlogDetailsRepoImpl: GetBlogDetailsRepoImpl

    lateinit var blog: Blog
    lateinit var blogs: Blogs

    lateinit var blogDTO: BlogDTO
    lateinit var blogsDto: BlogsDTO
    private lateinit var apiService: ApiService

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        Dispatchers.setMain(Dispatchers.Default)
        apiService = mockk<ApiService>()
        getBlogDetailsRepoImpl = GetBlogDetailsRepoImpl(apiService)
        blog= Blog(id="abc", image = "abnn.png", likes = 3, owner = Owner( firstName = "Test", id="aa", lastName = "ta",
            picture = "", title = ""), publishDate = "", tags = emptyList(),
            text = "desc")
        blogs= Blogs( listOf( blog), 5, 10, 50)

        blogDTO= BlogDTO(id="abc", image = "abnn.png", likes = 3, owner = OwnerDTO( firstName = "Test", id="aa", lastName = "ta",
            picture = "", title = ""), publishDate = "", tags = emptyList(),
            text = "desc")
        blogsDto= BlogsDTO( listOf( blogDTO), 5, 10, 50)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `test get blog by id success`() = runTest {
        coEvery { apiService.getBlogDetails(id="abc") } returns  Response.success(blogDTO)
        var res= getBlogDetailsRepoImpl.getBlogDetails("abc")

            Truth.assertThat(res?.id).isEqualTo(blog.id)

    }


}