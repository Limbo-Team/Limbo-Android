package com.igorj.quiz_data.di

import com.igorj.quiz_data.repository.QuestionsRepositoryImpl
import com.igorj.quiz_domain.repository.QuestionsRepository
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