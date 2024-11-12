package com.tcs.assignment.screens.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.tcs.common.Resource
import com.tcs.data.network.model.BlogDTO
import com.tcs.data.network.model.OwnerDTO
import com.tcs.data.repository.GetBlogDetailsRepoImpl
import com.tcs.domain.model.Blog
import com.tcs.domain.model.Owner
import com.tcs.domain.repository.GetBlogDetailsRepo
import com.tcs.domain.use_cases.GetBlogDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BlogDetailsViewModelTest{
    private lateinit var blogDetailsViewModel: BlogDetailsViewModel
    private lateinit var getBlogDetailsRepo: GetBlogDetailsRepo
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var getBlogDetailsUseCase: GetBlogDetailsUseCase
    private val testScope = TestCoroutineScope(testDispatcher)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)

    lateinit var blog: Blog

    @Before
    fun setUp() {
        blog= Blog(id="abc", image = "abnn.png", likes = 3, owner = Owner( firstName = "Test", id="aa", lastName = "ta",
            picture = "", title = ""), publishDate = "", tags = emptyList(),
            text = "desc")
        Dispatchers.setMain(testDispatcher)
        getBlogDetailsRepo = mockk<GetBlogDetailsRepo>()
        getBlogDetailsUseCase = mockk<GetBlogDetailsUseCase>()

        blogDetailsViewModel = BlogDetailsViewModel(
            getBlogDetailsUseCase
        )
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test get blog by ID`() = runTest {
        coEvery { getBlogDetailsUseCase.invoke("abc") }  returns flowOf(
            Resource.Success(blog))
                    blogDetailsViewModel.getBlogDetails("abc")
        assertThat(blogDetailsViewModel.details.value.data).isEqualTo(blog)
    }
}