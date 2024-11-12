package com.tcs.domain.use_cases

import com.google.common.truth.Truth
import com.tcs.common.Resource
import com.tcs.domain.model.Blog
import com.tcs.domain.model.Owner
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


class GetBlogsUseCaseTest{
    private lateinit var getBlogsRepository: GetBlogsRepository
    private lateinit var getBlogsUseCase: GetBlogsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        getBlogsRepository = mockk<GetBlogsRepository>()
        getBlogsUseCase = GetBlogsUseCase(getBlogsRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `test getBlogs `() = runTest {
        val blogs = listOf(
            Blog(id="abc", image = "abnn.png", likes = 3, owner = Owner( firstName = "Test", id="aa", lastName = "ta",
                picture = "", title = ""), publishDate = "", tags = emptyList(),
                text = "desc"),
            Blog(id="abc2", image = "abnn1.png", likes = 3, owner = Owner( firstName = "Test1", id="aa1", lastName = "ta",
                picture = "", title = ""), publishDate = "", tags = emptyList(),
                text = "desc1"),
            Blog(id="abc3", image = "abnn2.png", likes = 3, owner = Owner( firstName = "Test2", id="aa2", lastName = "ta",
                picture = "", title = ""), publishDate = "", tags = emptyList(),
                text = "desc2"),
        )
        coEvery { getBlogsRepository.getBlogs(1,10) } returns Resource.Success(blogs)
       var res= getBlogsUseCase.invoke(1,10)
        if(res?.data?.isNotEmpty()!!){
            Truth.assertThat(res.data).isEqualTo(blogs)
        }

    }

}