package com.postliu.openai.di

import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.domain.usecase.GetSmsCodeUseCase
import com.postliu.openai.domain.usecase.IGetSmsCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetSmsCodeUseCaseModule {

    @Provides
    @Singleton
    fun providesGetSmsCodeUseCase(repository: ILoginRepository): IGetSmsCodeUseCase {
        return GetSmsCodeUseCase(repository)
    }
}