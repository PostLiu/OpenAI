package com.postliu.openai.di

import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.domain.usecase.ILoginUseCase
import com.postliu.openai.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: ILoginRepository): ILoginUseCase {
        return LoginUseCase(repository)
    }
}