package com.quizapp.feature_quiz.domain.repository

import com.quizapp.feature_quiz.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    fun getQuestions(): Flow<List<Question>>

    suspend fun getQuestionById(id:Int): Question?

    suspend fun insertQuestion(note: Question)

    suspend fun deleteQuestion(note:Question)
}