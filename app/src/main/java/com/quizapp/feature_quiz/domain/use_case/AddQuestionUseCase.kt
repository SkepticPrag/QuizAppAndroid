package com.quizapp.feature_quiz.domain.use_case

import com.quizapp.feature_quiz.domain.model.InvalidQuestionException
import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.repository.QuestionRepository

class AddQuestionUseCase(
    private val repository: QuestionRepository
)
{
    @Throws(InvalidQuestionException::class)
    suspend operator fun invoke(question: Question)
    {
        if(question.question.isBlank())
        {
            throw InvalidQuestionException("The Question can't be empty")
        }
        if(question.optionOne.isBlank())
        {
            throw InvalidQuestionException("The Options can't be empty")
        }
        if(question.optionTwo.isBlank())
        {
            throw InvalidQuestionException("The Options can't be empty")
        }
        if(question.optionThree.isBlank())
        {
            throw InvalidQuestionException("The Options can't be empty")
        }
        repository.insertQuestion(question)
    }
}