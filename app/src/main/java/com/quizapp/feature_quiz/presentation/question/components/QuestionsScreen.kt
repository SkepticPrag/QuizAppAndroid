package com.quizapp.feature_quiz.presentation.question.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.quizapp.feature_quiz.presentation.util.Screen
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun QuestionsScreen(
    navController: NavController,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
    floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddEditQuestionScreen.route)
            },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
        }
    },
    scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Questions Database",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(QuestionsEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    questionOrder = state.questionOrder,
                    onOrderChange = {
                        viewModel.onEvent(QuestionsEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                items(state.questions)
                {
                        question -> QuestionItem(
                    question = question,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable
                        {
                            navController.navigate(Screen.AddEditQuestionScreen.route + "?questionId=${question.id}&questionCategory=${question.category}&questionDifficulty=${question.difficulty}")
                        },
                    onDeleteClick = {
                        viewModel.onEvent(QuestionsEvent.DeleteQuestion(question))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Question Deleted",
                                actionLabel = "Undo"
                            )
                            if(result == SnackbarResult.ActionPerformed){
                                viewModel.onEvent(QuestionsEvent.RestoreQuestion)
                            }
                        }
                    }
                )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}