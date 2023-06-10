package com.postliu.openai.di

import com.postliu.openai.domain.repository.ILoginRepository
import com.postliu.openai.domain.usecase.IRegisterUseCase
import com.postliu.openai.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterUseCaseModule {

    @Provides
    @Singleton
    fun providesRegisterUseCase(repository: ILoginRepository): IRegisterUseCase {
        return RegisterUseCase(repository)
    }
}