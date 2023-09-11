package com.igorj.limboapp.di

import com.igorj.core.data.auth.DefaultAuthAPI
import com.igorj.core.domain.auth.AuthAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthAPI {
        return DefaultAuthAPI()
    }
}