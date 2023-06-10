package com.postliu.openai.di

import com.postliu.openai.api.ApiService
import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginRepositoryModule {

    @Provides
    @Singleton
    fun providesLoginRepository(apiService: ApiService): ILoginRepository {
        return LoginRepository(apiService)
    }
}