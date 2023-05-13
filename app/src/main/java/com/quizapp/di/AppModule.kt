package com.quizapp.di

import android.app.Application
import androidx.room.Room
import com.quizapp.feature_quiz.data.data_source.QuestionDatabase
import com.quizapp.feature_quiz.data.repository.QuestionRepositoryImplementation
import com.quizapp.feature_quiz.domain.repository.QuestionRepository
import com.quizapp.feature_quiz.domain.use_case.GetQuestionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuestionDatabase(app: Application): QuestionDatabase
    {
        return Room.databaseBuilder(
            app,
            QuestionDatabase::class.java,
            QuestionDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(db:QuestionDatabase): QuestionRepository{
        return QuestionRepositoryImplementation(db.questionDao)
    }

    @Provides
    @Singleton
    fun provideQuestionUseCases(repository: QuestionDatabase): GetQuestionUseCase{
        return QuestionUseCases(
            getQuestion = GetQuestionUseCase(repository),
        )

    }
}
