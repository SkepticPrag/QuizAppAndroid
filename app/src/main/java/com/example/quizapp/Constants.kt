package com.example.quizapp

object Constants {
    fun getQuestions(): ArrayList<Questions>{
        val questionsList = ArrayList<Questions>()

        val question1 = Questions(1,"",R.drawable.ig_bg,"","","",1)

        questionsList.add(question1)
        return questionsList
    }
}