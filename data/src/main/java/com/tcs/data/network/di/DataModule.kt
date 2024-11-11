package com.tcs.data.network.di

import com.tcs.common.Constant
import com.tcs.data.network.ApiService
import com.tcs.data.repository.GetBlogDetailsRepoImpl
import com.tcs.data.repository.GetBlogsRepositoryImpl
import com.tcs.domain.repository.GetBlogDetailsRepo
import com.tcs.domain.repository.GetBlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(@Named("Retrofit") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    @Named("Retrofit")
    fun provideOkHttpClientForRetrofit(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideGetBlogsRepository(apiService: ApiService): GetBlogsRepository {
        return GetBlogsRepositoryImpl(apiService)
    }
    @Provides
    fun provideGetBlogDetailsRepo(apiService: ApiService): GetBlogDetailsRepo {
        return GetBlogDetailsRepoImpl(apiService)
    }

}