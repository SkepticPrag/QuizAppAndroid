package com.quizapp.feature_quiz.presentation.util

sealed class Screen(val route:String)
{
    object QuestionsScreen: Screen("questions_screen")
    object AddEditQuestionScreen: Screen("add_edit_question_screen")
    object QuizScreen: Screen("quiz_screen")

}