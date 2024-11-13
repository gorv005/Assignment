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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class GetBlogsRepositoryImplTest{
    @get:Rule
   private val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getBlogsRepositoryImpl: GetBlogsRepositoryImpl

    private lateinit var blog: Blog
    private lateinit var blogs:Blogs

    private lateinit var blogDTO: BlogDTO
    private lateinit var blogsDto:BlogsDTO
    private lateinit var apiService: ApiService

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        apiService = mockk<ApiService>()
        getBlogsRepositoryImpl = GetBlogsRepositoryImpl(apiService)
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
    fun `test get product success`() = runTest {
        coEvery { apiService.getBlogs(page = 1, limit = 10) } returns  Response.success(blogsDto)
        var res= getBlogsRepositoryImpl.getBlogs(1,10)
            if (res.data?.isNotEmpty()!!) {
                Truth.assertThat(res.data).isEqualTo(listOf(blog))
            }

    }
    @Test
    fun `test get product error`() = runTest {
        val responseBody: ResponseBody =
            "Response.error()".toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { apiService.getBlogs(page = 10, limit = 10) } returns  Response.error(500, responseBody)
        var res= getBlogsRepositoryImpl.getBlogs(10,10)
            if (res is Resource.Error) {
                Truth.assertThat(res.data).isNull()
                Truth.assertThat(res.message).isEqualTo("Response.error()")
            }
        }


}