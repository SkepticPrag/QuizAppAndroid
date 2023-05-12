package com.quizapp.feature_quiz.data.repository

import com.quizapp.feature_quiz.data.data_source.QuestionDao
import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.repository.QuestionRepository

import kotlinx.coroutines.flow.Flow
class QuestionRepositoryImplementation(
    private val dao:QuestionDao
): QuestionRepository {
    override fun getQuestions(): Flow<List<Question>> {
        return dao.getQuestions()
    }

    override suspend fun getQuestionById(id: Int): Question? {
        return dao.getQuestionById(id)
    }

    override suspend fun insertQuestion(question: Question) {
        dao.insertQuestion(question)
    }

    override suspend fun deleteQuestion(question: Question) {
        dao.deleteQuestion(question)
    }
}