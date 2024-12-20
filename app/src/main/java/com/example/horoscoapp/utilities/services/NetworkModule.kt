package com.example.horoscoapp.utilities.services

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) // Project level
object NetworkModule {
    @Provides
    fun provideNetwork(context: Context): NetworkIdentity {
        return NetworkIdentity(context)
    }
}