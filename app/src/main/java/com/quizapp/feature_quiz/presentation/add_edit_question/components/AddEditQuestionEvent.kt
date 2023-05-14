package com.quizapp.feature_quiz.presentation.add_edit_question.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditQuestionEvent  {
    data class EnteredQuestion(val value: String) : AddEditQuestionEvent()
    data class ChangeQuestionFocus(val focusState: FocusState): AddEditQuestionEvent()
    data class EnteredOptionOne(val value:String): AddEditQuestionEvent()
    data class ChangeOptionOneFocus(val focusState: FocusState): AddEditQuestionEvent()
    data class EnteredOptionTwo(val value:String): AddEditQuestionEvent()
    data class ChangeOptionTwoFocus(val focusState: FocusState): AddEditQuestionEvent()
    data class EnteredOptionThree(val value:String): AddEditQuestionEvent()
    data class ChangeOptionThreeFocus(val focusState: FocusState): AddEditQuestionEvent()
    data class EnteredAnswer(val value:Int): AddEditQuestionEvent()
    data class ChangeAnswerFocus(val focusState: FocusState): AddEditQuestionEvent()
    data class ChangeCategory(val category: Int): AddEditQuestionEvent()
    object SaveQuestion: AddEditQuestionEvent()
}