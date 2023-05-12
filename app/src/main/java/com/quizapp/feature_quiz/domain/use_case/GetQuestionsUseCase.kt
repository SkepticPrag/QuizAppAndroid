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
    operator fun invoke(questionOrder: QuestionOrder =QuestionOrder.RandomOrder(OrderType.Descending)): Flow<List<Question>>
    {
        return repository.getQuestions.map{notes->
            when (noteOrder.orderType)
            {
                is OrderType.Ascending->
                {
                    when(noteOrder)
                    {
                        is NoteOrder.Title-> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timeStamp }
                        is NoteOrder.Color->notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending->
                {
                    when(noteOrder)
                    {
                        is NoteOrder.Title-> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timeStamp }
                        is NoteOrder.Color->notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}