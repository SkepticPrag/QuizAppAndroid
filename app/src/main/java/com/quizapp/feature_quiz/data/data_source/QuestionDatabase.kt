package com.quizapp.feature_quiz.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quizapp.feature_quiz.domain.model.Question
@Database(
    entities = [Question::class],
    version = 1
)

abstract class QuestionDatabase {
    abstract val noteDao: QuestionDao

    companion object {
        const val DATABASE_NAME = "questions_db"
    }
}