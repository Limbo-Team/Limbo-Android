package com.igorj.dashboard_data.di

import com.igorj.dashboard_data.repository.ChaptersRepositoryImpl
import com.igorj.dashboard_data.repository.StatsRepositoryImpl
import com.igorj.dashboard_domain.repository.ChaptersRepository
import com.igorj.dashboard_domain.repository.StatsRepository
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
}