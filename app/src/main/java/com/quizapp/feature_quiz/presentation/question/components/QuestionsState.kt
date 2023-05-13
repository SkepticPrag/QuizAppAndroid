package com.quizapp.feature_quiz.presentation.question.components

import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.util.OrderType
import com.quizapp.feature_quiz.domain.util.QuestionOrder

data class QuestionsState(

    val questions: List<Question> = emptyList(),
    val questionOrder: QuestionOrder= QuestionOrder.Category(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false

)