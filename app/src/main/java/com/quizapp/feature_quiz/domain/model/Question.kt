package com.quizapp.feature_quiz.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quizapp.theme.*

@Entity
data class Question(
    val category: Int,
    val difficulty: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val correctAnswer : Int,
    @PrimaryKey val id:Int? = null
)
{
    companion object{
        val categoryList = listOf(scienceCategory, movieCategory , historyCategory, sportsCategory, worldCategory)
        val correctAnswerNumber = -1
        val difficultyNumber = -1
    }
}

class InvalidQuestionException(message:String):Exception(message)