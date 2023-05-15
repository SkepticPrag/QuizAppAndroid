package com.quizapp.feature_quiz.presentation.question.components


import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizapp.feature_quiz.domain.model.Question
import com.quizapp.feature_quiz.domain.use_case.QuestionUseCases
import com.quizapp.feature_quiz.domain.util.OrderType
import com.quizapp.feature_quiz.domain.util.QuestionOrder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel@Inject constructor(
    private val questionUseCases: QuestionUseCases
): ViewModel(){

    private var recentlyDeletedQuestion: Question? = null

    private var getQuestionsJob: Job? =null

    init
    {
        getQuestions(QuestionOrder.Category(OrderType.Descending))
    }

    private val _state = mutableStateOf(QuestionsState())
    val state: State<QuestionsState> = _state

    fun onEvent(event:QuestionsEvent)
    {
        when(event)
        {
            is QuestionsEvent.Order -> {
                if(state.value.questionOrder::class == event.questionOrder::class &&
                    state.value.questionOrder.orderType == event.questionOrder.orderType)
                {
                    return
                }
                getQuestions(event.questionOrder)
            }

            is QuestionsEvent.RestoreQuestion -> {
                viewModelScope.launch {
                    questionUseCases.addQuestion(recentlyDeletedQuestion?:return@launch)
                    recentlyDeletedQuestion=null
                }
            }
            is QuestionsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getQuestions(questionOrder: QuestionOrder)
    {
        getQuestionsJob?.cancel()
        getQuestionsJob = questionUseCases.getQuestions(questionOrder).onEach {
                questions-> _state.value = state.value.copy(questions = questions, questionOrder = questionOrder)
        }
            .launchIn(viewModelScope)
    }

}