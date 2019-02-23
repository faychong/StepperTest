package edu.uco.ychong.steppertest.model

import java.util.ArrayList

object Feelings {
    fun getAllFeelings() : ArrayList<String> {
        return arrayListOf("I'm feeling...",
            "Happy",
            "Sad",
            "Fearful",
            "Angry",
            "Surprised",
            "Disgusted")
    }
}