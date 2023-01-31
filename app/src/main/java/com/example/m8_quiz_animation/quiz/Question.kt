package com.example.m8_quiz_animation.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Question(
    val question:String,
    val answers:List<String>,
    val feedback:List<String>,
): Parcelable