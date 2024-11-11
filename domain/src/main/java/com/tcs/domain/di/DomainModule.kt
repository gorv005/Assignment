package com.tcs.domain.di

import com.tcs.domain.repository.GetBlogDetailsRepo
import com.tcs.domain.repository.GetBlogsRepository
import com.tcs.domain.use_cases.GetBlogDetailsUseCase
import com.tcs.domain.use_cases.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetBlogsUseCase(getBlogsRepository: GetBlogsRepository): GetBlogsUseCase {
        return GetBlogsUseCase(getBlogsRepository)
    }

    @Provides
    fun provideGetBlogDetailsUseCase(getBlogDetailsRepo: GetBlogDetailsRepo): GetBlogDetailsUseCase {
        return GetBlogDetailsUseCase(getBlogDetailsRepo)
    }
}