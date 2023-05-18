package com.quizapp.feature_quiz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quizapp.feature_quiz.presentation.util.Screen
import com.quizapp.feature_quiz.presentation.add_edit_question.components.AddEditQuestionScreen
import com.quizapp.theme.QuizAppAndroid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppAndroid() {
                Surface(color = MaterialTheme.colors.background)
                {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.QuestionsScreen.route
                    )
                    {
                        composable(route = Screen.QuestionsScreen.route){
                            QuestionsScreen(navController = navController)
                        }
                        composable(route = Screen.AddEditQuestionScreen.route + "?questionId={questionId}&questionCategory={questionCategory}&questionDifficulty={questionDifficulty}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                )
                                {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                )
                                {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            val color = it.arguments?.getInt("noteColor")?: -1
                            AddEditQuestionScreen(navController = navController, noteColor = color)
                        }
                    }
                }
            }
        }
    }
}