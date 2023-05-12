package com.quizapp.feature_quiz.domain.util

sealed class QuestionOrder(val orderType: OrderType)
{
    class Category(orderType:OrderType): QuestionOrder(orderType)
    class Difficulty(orderType:OrderType): QuestionOrder(orderType)


    fun copy(orderType: OrderType):QuestionOrder
    {
        return when(this)
        {
            is Category -> Category(orderType)
            is Difficulty ->Difficulty(orderType)
        }
    }
}