package com.quizapp.feature_quiz.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quizapp.theme.*

@Entity
data class Question(
    val difficulty: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val correctAnswer: Int,
    @PrimaryKey val id:Int? = null
)
{
    companion object{
        val category = listOf(scienceCategory, movieCategory , historyCategory, sportsCategory, worldCategory)
    }
}

class InvalidQuestionException(message:String):Exception(message)