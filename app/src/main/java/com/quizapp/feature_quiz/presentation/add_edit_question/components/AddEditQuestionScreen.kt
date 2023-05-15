package com.quizapp.feature_quiz.presentation.add_edit_question.components


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditQuestionScreen(
    navController: NavController,
    questionDifficulty: Int,
    questionCategory: Int,
    viewModel: AddEditQuestionViewModel = hiltViewModel()
)
{
    val questionState = viewModel.questionQuestion.value
    val optionOneState = viewModel.questionOptionOne.value
    val optionTwoState = viewModel.questionOptionTwo.value
    val optionThreeState = viewModel.questionOptionThree.value

    val scaffoldState = rememberScaffoldState()

    val questionCategoryAnimatable = remember{
        Animatable(
            Color(if(questionCategory != -1) questionCategory else viewModel.questionCategory.value)
        )
    }

    val questionDifficultyAnimatable = remember{
        Animatable(
            Color(if(questionDifficulty != -1) questionDifficulty else viewModel.questionDifficulty.value)
        )
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true)
    {
        viewModel.eventFlow.collectLatest { event ->
            when(event)
            {
                is AddEditQuestionViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                is AddEditQuestionViewModel.UiEvent.SaveQuestion ->{
                    navController.navigateUp()
                }
            }
        }
    }
}