package com.quizapp.feature_quiz.presentation.add_edit_question.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizapp.feature_quiz.domain.model.InvalidQuestionException
import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.use_case.QuestionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditQuestionViewModel @Inject constructor(
    private val questionUseCases: QuestionUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _questionCategory = mutableStateOf(Question.categoryList.random().MAX_VALUE)
    val questionCategory: State<Int> = _questionCategory

    private val _questionDifficulty = mutableStateOf(Question.difficultyNumber)
    val questionDifficulty: State<Int> = _questionDifficulty

    private val _questionQuestion = mutableStateOf(QuestionTextFieldState(hint = "Enter Question..."))
    val questionQuestion: State<QuestionTextFieldState> = _questionQuestion

    private val _questionOptionOne = mutableStateOf(QuestionTextFieldState(hint = "Enter First Option..."))
    val questionOptionOne: State<QuestionTextFieldState> = _questionOptionOne

    private val _questionOptionTwo = mutableStateOf(QuestionTextFieldState(hint = "Enter Second Option..."))
    val questionOptionTwo: State<QuestionTextFieldState> = _questionOptionTwo

    private val _questionOptionThree = mutableStateOf(QuestionTextFieldState(hint = "Enter Third Option..."))
    val questionOptionThree: State<QuestionTextFieldState> = _questionOptionThree

    private val _questionAnswer = mutableStateOf(Question.correctAnswerNumber)
    val questionAnswer: State<Int> = _questionAnswer



    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                        _questionDifficulty.value = question.difficulty
                        _questionCategory.value = question.category

                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditQuestionEvent)
    {
        when(event)
        {
            is AddEditQuestionEvent.EnteredQuestion->{
                _questionQuestion.value = questionQuestion.value.copy(text = event.value)
            }
            is AddEditQuestionEvent.ChangeQuestionFocus ->
            {
                _questionQuestion.value= questionQuestion.value.copy(isHintVisible = !event.focusState.isFocused && questionQuestion.value.text.isBlank())
            }
            is AddEditQuestionEvent.EnteredOptionOne->{
                _questionOptionOne.value = questionOptionOne.value.copy(text = event.value)
            }
            is AddEditQuestionEvent.ChangeOptionOneFocus ->
            {
                _questionOptionOne.value= questionOptionOne.value.copy(isHintVisible = !event.focusState.isFocused && questionOptionOne.value.text.isBlank())
            }
            is AddEditQuestionEvent.EnteredOptionTwo->{
                _questionOptionTwo.value = questionOptionTwo.value.copy(text = event.value)
            }
            is AddEditQuestionEvent.ChangeOptionTwoFocus ->
            {
                _questionOptionTwo.value= questionOptionTwo.value.copy(isHintVisible = !event.focusState.isFocused && questionOptionTwo.value.text.isBlank())
            }
            is AddEditQuestionEvent.EnteredOptionThree->{
                _questionOptionOne.value = questionOptionThree.value.copy(text = event.value)
            }
            is AddEditQuestionEvent.ChangeOptionThreeFocus ->
            {
                _questionOptionThree.value= questionOptionThree.value.copy(isHintVisible = !event.focusState.isFocused && questionOptionThree.value.text.isBlank())
            }

            is AddEditQuestionEvent.EnteredAnswer ->{
                _questionAnswer.value = event.value
            }

            is AddEditQuestionEvent.ChangeCategory ->{
                _questionCategory.value = event.category
            }
            is AddEditQuestionEvent.SaveQuestion -> {
                viewModelScope.launch {
                    try
                    {
                        questionUseCases.addQuestion(
                            Question(
                                category = questionCategory.value,
                                difficulty = questionDifficulty.value,
                                question = questionQuestion.value.text,
                                optionOne = questionOptionOne.value.text,
                                optionTwo = questionOptionTwo.value.text,
                                optionThree = questionOptionThree.value.text,
                                correctAnswer = questionAnswer.value,

                                id = currentQuestionId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveQuestion)
                    } catch (e: InvalidQuestionException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(message = e.message?: "Couldn't save question")
                        )
                    }
                }
            }
        }
    }
    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveQuestion: UiEvent()
    }
}