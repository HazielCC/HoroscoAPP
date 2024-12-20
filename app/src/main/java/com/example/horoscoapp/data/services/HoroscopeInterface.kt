package com.example.horoscoapp.data.services

import com.example.horoscoapp.data.RepositoryImpl
import com.example.horoscoapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object HoroscopeInterface {

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiServices {
        return retrofit.create(HoroscopeApiServices::class.java)
    }

    @Provides
    fun provideRepository(apiServices: HoroscopeApiServices): Repository {
        return RepositoryImpl(apiServices)
    }
}