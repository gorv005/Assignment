package com.tcs.domain.use_cases

import com.google.common.truth.Truth
import com.tcs.common.Resource
import com.tcs.domain.model.Blog
import com.tcs.domain.model.Owner
import com.tcs.domain.repository.GetBlogDetailsRepo
import com.tcs.domain.repository.GetBlogsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class GetBlogDetailsUseCaseTest{

    private lateinit var getBlogDetailsRepo: GetBlogDetailsRepo
    private lateinit var getBlogDetailsUseCase: GetBlogDetailsUseCase
    private lateinit var blog: Blog
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        getBlogDetailsRepo = mockk<GetBlogDetailsRepo>()
        getBlogDetailsUseCase = GetBlogDetailsUseCase(getBlogDetailsRepo)
        blog=Blog(id="abc", image = "abnn.png", likes = 3, owner = Owner( firstName = "Test", id="aa", lastName = "ta",
            picture = "", title = ""), publishDate = "", tags = emptyList(),
            text = "desc")
        coEvery { getBlogDetailsRepo.getBlogDetails("abc") } returns  blog!!
       coEvery {  getBlogDetailsRepo.getBlogDetails("nnn") } returns  null
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `test get Blog By ID error`() = runTest {
        getBlogDetailsUseCase.invoke("nnn").collect {
            Truth.assertThat(it.data).isNull()
        }
    }

    @Test
    fun `test get Blog By ID success`() = runTest {
        getBlogDetailsUseCase.invoke("abc").collect {
            Truth.assertThat(it.data?.id).isEqualTo( blog.id)
        }
    }

}