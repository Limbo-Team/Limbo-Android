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
import com.igorj.limboapp.repository.interfaces.QuizApi
import com.igorj.limboapp.repository.interfaces.StatsRepository
import com.igorj.limboapp.repository.interfaces.UserRepository
import com.igorj.limboapp.use_case.FilterOutStudentIdDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    @Singleton
    fun provideFilterOutStudentIdDigits(): FilterOutStudentIdDigits {
        return FilterOutStudentIdDigits()
    }

    @Provides
    @Singleton
    fun provideStatsRepository(authApi: AuthAPI, @ApplicationContext appContext: Context): StatsRepository {
        return StatsRepositoryImpl(authApi, appContext)
    }

    @Provides
    @Singleton
    fun provideChaptersRepository(authApi: AuthAPI, quizApi: QuizApi, @ApplicationContext appContext: Context): ChaptersRepository {
        return ChaptersRepositoryImpl(authApi, quizApi, appContext)
    }

    @Provides
    @Singleton
    fun provideUserRepository(authApi: AuthAPI, @ApplicationContext appContext: Context): UserRepository {
        return UserRepositoryImpl(authApi, appContext)
    }

    @Provides
    @Singleton
    fun provideQuestionsRepository(): QuestionsRepository {
        return QuestionsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://limbo-backend.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authApi: AuthAPI): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${authApi.getToken()}")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideQuizApi(retrofit: Retrofit): QuizApi {
        return retrofit.create(QuizApi::class.java)
    }
}