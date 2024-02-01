package com.igorj.limboapp.di

import com.igorj.limboapp.repository.implementation.QuestionsRepositoryImpl
import com.igorj.limboapp.repository.interfaces.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object QuizModule {

    @Provides
    @ViewModelScoped
    fun provideQuestionsRepository(): QuestionsRepository {
        return QuestionsRepositoryImpl()
    }
}