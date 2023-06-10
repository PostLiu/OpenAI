package com.postliu.openai.di

import com.postliu.openai.BuildConfig
import com.postliu.openai.interceptors.AddTokenHeaderInterceptor
import com.postliu.openai.interceptors.TokenExpiredInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkhttpClientModule {

    @Provides
    @Singleton
    fun providesOkhttpClient(): OkHttpClient {
        val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(level)
        return OkHttpClient.Builder()
            .addInterceptor(AddTokenHeaderInterceptor)
            .addInterceptor(TokenExpiredInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}