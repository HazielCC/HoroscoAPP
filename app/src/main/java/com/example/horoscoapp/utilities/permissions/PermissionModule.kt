package com.example.horoscoapp.utilities.permissions

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Project level
object PermissionModule {

    @Provides
    @Singleton
    fun providePermission(context: Context): PermissionsService {
        return PermissionsService(context)
    }
}