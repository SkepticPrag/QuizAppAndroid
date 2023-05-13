package com.quizapp.feature_quiz.presentation.add_edit_question.components

data class QuestionTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)