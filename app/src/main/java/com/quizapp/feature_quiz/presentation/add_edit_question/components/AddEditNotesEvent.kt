package com.quizapp.feature_quiz.presentation.add_edit_question.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditQuestionsEvent  {
    data class EnteredQuestion(val value: String) : AddEditQuestionsEvent()
    data class ChangeQuestionFocus(val focusState: FocusState): AddEditQuestionsEvent()
    data class EnteredAnswer(val value:String): AddEditQuestionsEvent()
    data class ChangeAnswerFocus(val focusState: FocusState): AddEditQuestionsEvent()
    data class EnteredOptionOne(val value:String): AddEditQuestionsEvent()
    data class ChangeOptionOneFocus(val focusState: FocusState): AddEditQuestionsEvent()
    data class EnteredOptionTwo(val value:String): AddEditQuestionsEvent()
    data class ChangeOptionTwoFocus(val focusState: FocusState): AddEditQuestionsEvent()
    data class ChangeCategory(val category: String): AddEditQuestionsEvent()
    object SaveQuestion: AddEditQuestionsEvent()
}