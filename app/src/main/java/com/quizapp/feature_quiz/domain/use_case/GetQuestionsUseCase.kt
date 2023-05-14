package com.quizapp.feature_quiz.domain.use_case

import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.repository.QuestionRepository
import com.quizapp.feature_quiz.domain.util.QuestionOrder
import com.quizapp.feature_quiz.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class GetQuestionsUseCase(
    private val repository: QuestionRepository
)
{
    operator fun invoke(questionOrder: QuestionOrder =QuestionOrder.Category(OrderType.Descending)): Flow<List<Question>>
    {
        return repository.getQuestions().map{questions->
            when (questionOrder.orderType)
            {
                is OrderType.Ascending->
                {
                    when(questionOrder)
                    {
                        is QuestionOrder.Difficulty-> questions.sortedBy { it.difficulty }
                        is QuestionOrder.Category -> questions.sortedBy { it.category }
                    }
                }
                is OrderType.Descending->
                {
                    when(questionOrder)
                    {
                        is QuestionOrder.Difficulty-> questions.sortedByDescending { it.difficulty}
                        is QuestionOrder.Category -> questions.sortedByDescending { it.category }
                    }
                }
            }
        }
    }
}