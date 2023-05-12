package com.quizapp.feature_quiz.domain.util

sealed class QuestionOrder(val orderType: OrderType)
{
    class RandomOrder(orderType:OrderType): QuestionOrder(orderType)
    class Science(orderType:OrderType): QuestionOrder(orderType)
    class History(orderType:OrderType): QuestionOrder(orderType)


    fun copy(orderType: OrderType):QuestionOrder
    {
        return when(this)
        {
            is RandomOrder -> RandomOrder(orderType)
            is Science ->Science(orderType)
            is History -> History(orderType)
        }
    }
}