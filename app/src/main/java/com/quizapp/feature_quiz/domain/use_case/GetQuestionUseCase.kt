package com.quizapp.feature_quiz.domain.use_case

import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.repository.QuestionRepository


class GetQuestionUseCase(
    private val repository: QuestionRepository
) {
    suspend operator fun invoke(id:Int): Question?{
        return repository.getQuestionById(id)
    }
}