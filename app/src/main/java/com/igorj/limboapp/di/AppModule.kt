package com.igorj.limboapp.di

import android.content.Context
import com.igorj.core.data.auth.AuthSharedPreferences
import com.igorj.core.data.auth.DefaultAuthAPI
import com.igorj.core.domain.auth.AuthAPI
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