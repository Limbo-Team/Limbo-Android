package com.igorj.limboapp.di

import com.igorj.limboapp.repository.implementation.ChaptersRepositoryImpl
import com.igorj.limboapp.repository.implementation.StatsRepositoryImpl
import com.igorj.limboapp.repository.implementation.UserRepositoryImpl
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import com.igorj.limboapp.repository.interfaces.StatsRepository
import com.igorj.limboapp.repository.interfaces.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DashboardDomainModule {

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
}