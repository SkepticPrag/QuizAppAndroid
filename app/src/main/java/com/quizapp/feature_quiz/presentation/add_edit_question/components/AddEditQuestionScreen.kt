package com.quizapp.feature_quiz.presentation.add_edit_question.components


import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.quizapp.feature_quiz.domain.model.Question
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
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditQuestionEvent.SaveQuestion)
                },
                backgroundColor = MaterialTheme.colors.primary
            ){
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Question")
            }

        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Question.categoryList.forEach { category ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.questionCategory.value == category) {
                                    Color.Black
                                } else Color.Black,
                                shape = CircleShape
                            )
                            .clickable {
                                    viewModel.onEvent(AddEditQuestionEvent.ChangeCategory(category))
                            }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(text = questionState.text, hint = questionState.hint, onValueChange = {
                    viewModel.onEvent(AddEditQuestionEvent.EnteredQuestion(it))},
                    onFocusChange = {
                        viewModel.onEvent(AddEditQuestionEvent.ChangeQuestionFocus(it))
                    },
                    isHintVisible = questionState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(text = optionOneState.text, hint = optionOneState.hint, onValueChange = {
                    viewModel.onEvent(AddEditQuestionEvent.EnteredOptionOne(it))},
                    onFocusChange = {
                        viewModel.onEvent(AddEditQuestionEvent.ChangeOptionOneFocus(it))
                    },
                    isHintVisible = optionOneState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )

                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(text = optionTwoState.text, hint = optionTwoState.hint, onValueChange = {
                    viewModel.onEvent(AddEditQuestionEvent.EnteredOptionTwo(it))},
                    onFocusChange = {
                        viewModel.onEvent(AddEditQuestionEvent.ChangeOptionTwoFocus(it))
                    },
                    isHintVisible = optionTwoState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )

                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(text = optionThreeState.text, hint = optionThreeState.hint, onValueChange = {
                    viewModel.onEvent(AddEditQuestionEvent.EnteredOptionThree(it))},
                    onFocusChange = {
                        viewModel.onEvent(AddEditQuestionEvent.ChangeOptionThreeFocus(it))
                    },
                    isHintVisible = optionOneState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}