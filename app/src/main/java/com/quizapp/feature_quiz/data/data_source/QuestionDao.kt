package com.quizapp.feature_quiz.data.data_source

import androidx.room.*
import com.quizapp.feature_quiz.domain.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("Select * from question")
    fun getQuestions(): Flow<List<Question>>

    @Query("Select * from question where id = :id")
    suspend fun getQuestionById(id:Int):Question?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)


}