package com.igorj.limboapp.di

import com.igorj.limboapp.use_case.FilterOutStudentIdDigits
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
    fun provideFilterOutStudentIdDigits(): com.igorj.limboapp.use_case.FilterOutStudentIdDigits {
        return com.igorj.limboapp.use_case.FilterOutStudentIdDigits()
    }
}