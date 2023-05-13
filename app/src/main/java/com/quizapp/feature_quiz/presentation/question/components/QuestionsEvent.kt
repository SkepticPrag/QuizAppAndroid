package com.quizapp.feature_quiz.presentation.question.components

import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.util.QuestionOrder

sealed class QuestionsEvent {
    data class Order(val questionOrder: QuestionOrder): QuestionsEvent()
    data class DeleteQuestion(val question: Question): QuestionsEvent()
    object RestoreQuestion: QuestionsEvent()
    object ToggleOrderSection: QuestionsEvent()
}