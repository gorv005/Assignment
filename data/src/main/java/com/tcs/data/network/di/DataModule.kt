package com.tcs.data.network.di

import com.tcs.common.Constant
import com.tcs.data.network.ApiService
import com.tcs.data.repository.GetBlogsRepositoryImpl
import com.tcs.domain.repository.GetBlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        )
            .build()
    }

    @Provides
    fun provideGetBlogsRepository(apiService: ApiService): GetBlogsRepository {
        return GetBlogsRepositoryImpl(apiService)
    }
}