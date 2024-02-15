package com.example.gym_app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate
import java.util.Date

class WorkoutDay(){
    @RequiresApi(Build.VERSION_CODES.O)
    val date: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
    val description: MutableState<String> = mutableStateOf("")
    val exercises: MutableList<Exercise> = mutableListOf()

}

