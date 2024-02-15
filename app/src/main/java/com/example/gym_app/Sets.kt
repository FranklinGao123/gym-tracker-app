package com.example.gym_app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

data class Sets(
    var weight: MutableState<Int> = mutableIntStateOf(0),
    var reps: MutableState<Int> = mutableIntStateOf(0)
){

    var isCompleted: MutableState<Boolean> = mutableStateOf(false)
}
