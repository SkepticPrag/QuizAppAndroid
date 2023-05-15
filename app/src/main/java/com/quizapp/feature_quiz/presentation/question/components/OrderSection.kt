package com.quizapp.feature_quiz.presentation.question.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quizapp.feature_quiz.data.repository.QuestionRepositoryImplementation
import com.quizapp.feature_quiz.domain.util.OrderType
import com.quizapp.feature_quiz.domain.util.QuestionOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    questionOrder: QuestionOrder = QuestionOrder.Category(OrderType.Descending),
    onOrderChange: (QuestionOrder)->Unit
)
{
    Column (
        modifier =  modifier
    )
    {

    }
}