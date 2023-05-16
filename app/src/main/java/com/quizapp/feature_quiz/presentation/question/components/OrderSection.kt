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
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Difficulty",
                selected = questionOrder is QuestionOrder.Difficulty,
                onSelect = {onOrderChange(QuestionOrder.Difficulty(questionOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Category",
                selected = questionOrder is QuestionOrder.Category,
                onSelect = {onOrderChange(QuestionOrder.Category(questionOrder.orderType))
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultRadioButton(
                text = "Ascending",
                selected = questionOrder.orderType is OrderType.Ascending,
                onSelect = {onOrderChange(questionOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = questionOrder.orderType is OrderType.Descending,
                onSelect = {onOrderChange(questionOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}