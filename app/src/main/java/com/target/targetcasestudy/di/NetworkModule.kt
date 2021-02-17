package com.target.targetcasestudy.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.target.targetcasestudy.network.services.DealService
import com.target.targetcasestudy.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun providesLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return JacksonConverterFactory.create(ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES))
    }

    @Provides
    fun providesRetrofit(
        httpClient: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(factory)
            .client(httpClient)
            .build()
    }

    @Provides
    fun providesDealService(retrofit: Retrofit) = retrofit.create(DealService::class.java)
}