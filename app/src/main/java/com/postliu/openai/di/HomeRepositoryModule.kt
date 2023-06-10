package com.postliu.openai.di

import com.postliu.openai.api.ApiService
import com.postliu.openai.domain.repository.HomeRepository
import com.postliu.openai.domain.repository.IHomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun providesHomeRepository(apiService: ApiService): IHomeRepository {
        return HomeRepository(apiService)
    }
}