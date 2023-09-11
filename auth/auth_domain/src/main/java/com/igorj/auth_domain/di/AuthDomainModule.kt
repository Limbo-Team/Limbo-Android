package com.igorj.auth_domain.di

import com.igorj.auth_domain.use_case.FilterOutStudentIdDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthDomainModule {

    @Provides
    @ViewModelScoped
    fun provideFilterOutStudentIdDigits(): FilterOutStudentIdDigits {
        return FilterOutStudentIdDigits()
    }
}