package com.example.gym_app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Exercise(
    val name: MutableState<String> = mutableStateOf("Sample Exercise"),
    var sets: SnapshotStateList<Sets> = mutableStateListOf(),
    val picture: MutableState<Int> = mutableIntStateOf(R.drawable.temp)
){

    var reps: Int = sets.sumOf{ it.reps.value } / sets.size
    var weight: Int = sets.sumOf{ it.weight.value } / sets.size

    fun recalculateReps(){
        reps = sets.sumOf{ it.reps.value } / sets.size
    }

    fun recalculateWeight(){
        weight = sets.sumOf{ it.weight.value } / sets.size
    }

}