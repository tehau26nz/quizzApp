package com.tehaunuiss.myquizzapp

object Constants {

    // Storage keys to retrieve the data when switching from one activity to the other
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"


    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        // Question 1
        val question1 = Question(
            1 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina","Uruguay",
            "Armenia", "Chile",
            1
        )
        questionsList.add(question1)

        // Question 2
        val question2 = Question(
            2 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_samoa,
            "Fiji","New Zealand",
            "Samoa", "Kiribati Islands",
            3
        )
        questionsList.add(question2)

        // Question 3
        val question3 = Question(
            3 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_french_polynesia,
            "New Caledonia","French Polynesia",
            "Fiji", "Tonga",
            2
        )
        questionsList.add(question3)

        // Question 4
        val question4 = Question(
            4 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Paraguay","Costa Rica",
            "Venezuela", "Brazil",
            4
        )
        questionsList.add(question4)

        // Question 5
        val question5 = Question(
            5 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Belgium","Germany",
            "Luxembourg", "Armenia",
            2
        )
        questionsList.add(question5)

        // Question 6
        val question6 = Question(
            6 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Argentina","Vanuatu",
            "Fiji", "Cook Islands",
            3
        )
        questionsList.add(question6)

        // Question 7
        val question7 = Question(
            7 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "New Zealand","Australia",
            "Tonga", "Canada",
            2
        )
        questionsList.add(question7)

        // Question 8
        val question8 = Question(
            8 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_the_netherlands,
            "France","Denmark",
            "Andorra", "The Netherlands",
            4
        )
        questionsList.add(question8)

        // Question 9
        val question9 = Question(
            9 , "Which country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Pakistan","India",
            "Iran", "Egypt",
            2
        )
        questionsList.add(question9)
        return questionsList
    }
}