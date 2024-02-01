package com.igorj.limboapp.di

import android.content.Context
import com.igorj.limboapp.repository.implementation.AuthSharedPreferences
import com.igorj.limboapp.repository.implementation.ChaptersRepositoryImpl
import com.igorj.limboapp.repository.implementation.DefaultAuthAPI
import com.igorj.limboapp.repository.implementation.QuestionsRepositoryImpl
import com.igorj.limboapp.repository.implementation.StatsRepositoryImpl
import com.igorj.limboapp.repository.implementation.UserRepositoryImpl
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import com.igorj.limboapp.repository.interfaces.QuestionsRepository
import com.igorj.limboapp.repository.interfaces.StatsRepository
import com.igorj.limboapp.repository.interfaces.UserRepository
import com.igorj.limboapp.use_case.FilterOutStudentIdDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
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

    @Provides
    @ViewModelScoped
    fun provideFilterOutStudentIdDigits(): FilterOutStudentIdDigits {
        return FilterOutStudentIdDigits()
    }

    @Provides
    @ViewModelScoped
    fun provideStatsRepository(): StatsRepository {
        return StatsRepositoryImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideChaptersRepository(): ChaptersRepository {
        return ChaptersRepositoryImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideQuestionsRepository(): QuestionsRepository {
        return QuestionsRepositoryImpl()
    }
}