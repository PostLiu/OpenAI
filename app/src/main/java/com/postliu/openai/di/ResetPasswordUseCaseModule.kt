package com.postliu.openai.di

import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.domain.usecase.IResetPasswordUseCase
import com.postliu.openai.domain.usecase.ResetPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResetPasswordUseCaseModule {

    @Provides
    @Singleton
    fun providesResetPasswordUseCase(repository: ILoginRepository): IResetPasswordUseCase {
        return ResetPasswordUseCase(repository)
    }
}