package com.example.serinotechicalexam.di

import com.example.serinotechicalexam.data.datasource.ProductApi
import com.example.serinotechicalexam.data.repository.ProductRepoImp
import com.example.serinotechicalexam.domain.repository.ProductRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpClient() :OkHttpClient{

        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient
            .addInterceptor(interceptor)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ProductApi{
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepo(productApi: ProductApi): ProductRepo{
        return ProductRepoImp(productApi)
    }
}