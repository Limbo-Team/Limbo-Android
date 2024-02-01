package com.igorj.limboapp.di

import android.content.Context
import com.igorj.limboapp.repository.implementation.AuthSharedPreferences
import com.igorj.limboapp.repository.implementation.DefaultAuthAPI
import com.igorj.limboapp.repository.interfaces.AuthAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        @ApplicationContext appContext: Context,
        authSharedPreferences: AuthSharedPreferences
    ): AuthAPI {
        return DefaultAuthAPI(appContext, authSharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthSharedPreferences(@ApplicationContext appContext: Context): AuthSharedPreferences {
        return AuthSharedPreferences(appContext)
    }
}