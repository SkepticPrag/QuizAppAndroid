package com.quizapp.feature_quiz.presentation.add_edit_question.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.use_case.QuestionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionUseCases: QuestionUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _questionQuestion = mutableStateOf(QuestionTextFieldState(hint = "Enter Question..."))
    val questionQuestion: State<QuestionTextFieldState> = _questionQuestion

    private val _questionOptionOne = mutableStateOf(QuestionTextFieldState(hint = "Enter First Option..."))
    val questionOptionOne: State<QuestionTextFieldState> = _questionOptionOne

    private val _questionOptionTwo = mutableStateOf(QuestionTextFieldState(hint = "Enter Second Option..."))
    val questionOptionTwo: State<QuestionTextFieldState> = _questionOptionTwo

    private val _questionOptionThree = mutableStateOf(QuestionTextFieldState(hint = "Enter Three Option..."))
    val questionOptionThree: State<QuestionTextFieldState> = _questionOptionThree

    private val _questionAnswer = mutableStateOf(Question.answer)
    val questionAnswer: State<Int> = _questionAnswer

    private val _questionCategory = mutableStateOf(Question.category.random().toString())
    val questionCategory: State<String> = _questionCategory

    private var currentQuestionId:Int? = null

    init {
        savedStateHandle.get<Int>("questionId")?.let{questionId ->
            if(questionId != -1)
            {
                viewModelScope.launch {
                    questionUseCases.getQuestion(questionId)?.also {question ->
                        currentQuestionId = question.id
                        _questionQuestion.value = questionQuestion.value.copy(
                            text = question.question,
                            isHintVisible = false
                        )
                        _questionOptionOne.value = questionOptionOne.value.copy(
                            text = question.optionOne,
                            isHintVisible = false
                        )

                        _questionOptionTwo.value = questionOptionTwo.value.copy(
                            text = question.optionTwo,
                            isHintVisible = false
                        )

                        _questionOptionThree.value = questionOptionThree.value.copy(
                            text = question.optionThree,
                            isHintVisible = false
                        )

                        _questionAnswer.value = question.correctAnswer

                        _questionCategory.value = questionCategory.toString()

                    }
                }
            }
        }
    }
}