package com.example.gym_app

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.Icon
import android.widget.NumberPicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.isDigitsOnly
import com.example.gym_app.ui.theme.Gym_AppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

var isOpened: SnapshotStateList<Boolean> = mutableStateListOf()

@Composable
fun Set(i: Int, exercise: Exercise, modifier: Modifier = Modifier){
    Column (verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable {
                //Consume Parent Click
            }
            ){
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                isOpened[i] = !isOpened[i]
                if (isOpened[i]) {
                    for (j in 0..<isOpened.size) {
                        if (i != j) {
                            isOpened[j] = false
                        }
                    }
                }
            }
            .padding(10.dp)
        ) {
            Row (horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Column(modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)) {
                    Text(text = exercise.name.value,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold)
                    if(!isOpened[i]){
                        Text(text = exercise.sets.size.toString() +" sets • "+exercise.reps.toString() + " reps • " + exercise.weight.toString() + " lbs")
                    }
                }
                Image(
                    painter = painterResource(id = exercise.picture.value),
                    contentDescription = exercise.name.value,
                    alignment = Alignment.TopEnd,
                    modifier = Modifier
                        .size(80.dp)
                        .weight(1f)
                )
            }
        }
        if(isOpened[i]){
            Divider(modifier = Modifier.padding(10.dp, 0.dp))
            Column(modifier = Modifier
                .absolutePadding(10.dp, 0.dp, 10.dp, 10.dp),
                verticalArrangement = Arrangement.Center) {
                Row (modifier.absolutePadding(0.dp,0.dp,0.dp,4.dp)){
                    Text(text = "Set",
                        modifier = Modifier.weight(0.5f),
                        textAlign = TextAlign.Center
                    )
                    Text(text = "Lbs",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center)
                    Text(text = "Reps",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center)
                    Box(modifier = Modifier
                        .weight(0.5f)
                    ){
                        Box(modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center))
                        {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "")
                        }
                    }
                }
                for (j in 1..exercise.sets.size){
                    var isDeleting by remember { mutableStateOf(false) }
                    Row(
                        modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(
                                (if (!exercise.sets[j - 1].isCompleted.value) Color.White else Color(
                                    216,
                                    252,
                                    210
                                ))
                            )
                            .absolutePadding(0.dp, 5.dp, 0.dp, 5.dp)
                            .pointerInput(Unit) {
                                detectHorizontalDragGestures { change, dragAmount ->
                                    if (dragAmount <= -20) {
                                        isDeleting = true
                                    }
                                    if (dragAmount >= 20) {
                                        isDeleting = false
                                    }
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(text = j.toString(),
                            modifier = Modifier.weight(0.5f),
                            textAlign = TextAlign.Center
                        )
                        Box(modifier = Modifier
                            .weight(1f)){
                            Box(modifier = Modifier
                                //.size(22.5.dp)
                                .clip(shape = RoundedCornerShape(5.dp))
                                .border(1.5.dp, Color(235, 235, 235, 255))
                                .align(Alignment.Center)) {
                                BasicTextField(
                                    modifier = Modifier
                                        .height(22.5.dp)
                                        .width(50.dp),
                                    value = exercise.sets[j - 1].weight.value.toString(),
                                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black, textAlign = TextAlign.Center),
                                    onValueChange = {
                                        if (it.isNotEmpty() && it.isDigitsOnly()){
                                            if(exercise.sets[j - 1].weight.value == 0){
                                                var temp = it.removeRange(it.indexOf('0'),it.indexOf('0')+1)
                                                exercise.sets[j - 1].weight.value = temp.toInt()
                                            }else{
                                                exercise.sets[j - 1].weight.value = it.toInt()
                                            }
                                        }else{
                                            exercise.sets[j - 1].weight.value = 0
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )
                            }
                        }
                        Box(modifier = Modifier
                            .weight(1f)){
                            Box(modifier = Modifier
                                //.size(22.5.dp)
                                .clip(shape = RoundedCornerShape(5.dp))
                                .border(1.5.dp, Color(235, 235, 235, 255))
                                .align(Alignment.Center)) {
                                BasicTextField(
                                    modifier = Modifier
                                        .height(22.5.dp)
                                        .width(50.dp),
                                    value = exercise.sets[j - 1].reps.value.toString(),
                                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black, textAlign = TextAlign.Center),
                                    onValueChange = {
                                        if (it.isNotEmpty() && it.isDigitsOnly()){
                                            if(exercise.sets[j - 1].reps.value == 0){
                                                var temp = it.removeRange(it.indexOf('0'),it.indexOf('0')+1)
                                                exercise.sets[j - 1].reps.value = temp.toInt()
                                            }else{
                                                exercise.sets[j - 1].reps.value = it.toInt()
                                            }
                                        }else{
                                            exercise.sets[j - 1].reps.value = 0
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )
                            }
                        }

//                        Text(text = exercise.sets[j-1].reps.value.toString(),
//                            modifier = Modifier.weight(1f),
//                            textAlign = TextAlign.Center)
                        Box(modifier = Modifier
                            .weight(0.5f)
                            ){
                            Box(modifier = Modifier
                                .size(20.dp)
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(
                                    if (!exercise.sets[j - 1].isCompleted.value) Color(
                                        235,
                                        235,
                                        235,
                                        255
                                    ) else Color.Green
                                )
                                .align(Alignment.Center)
                                .clickable {
                                    exercise.sets[j - 1].isCompleted.value =
                                        !exercise.sets[j - 1].isCompleted.value
                                })
                            {
                                Icon(imageVector = Icons.Default.Check,
                                    contentDescription = "",
                                    tint = (if(!exercise.sets[j-1].isCompleted.value) Color.Black else Color.White))
                            }
                        }
                        if(isDeleting){
                            Box(modifier = Modifier
                                .weight(0.2f)
                                .padding(2.dp)
                            ) {
                                Box(modifier = Modifier
                                    .size(15.dp)
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .background(Color.Red)
                                    .align(Alignment.Center)
                                    .clickable {
                                        exercise.sets.removeAt(j - 1)
                                        isDeleting = false
                                        if (exercise.sets.size == 0) {
                                            isOpened.removeAt(i)
                                            exercises.removeAt(i)
                                        } else {
                                            exercise.recalculateReps()
                                            exercise.recalculateWeight()
                                        }
                                    })
                                {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
                Box(modifier = Modifier
                    .padding(0.dp, 7.dp)
                    .align(Alignment.End)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color(219, 235, 250, 255))
                    .absolutePadding(5.dp, 0.dp, 5.dp, 2.dp)
                    .clickable {
                        exercise.sets.add((Sets(mutableIntStateOf(0), mutableIntStateOf(0))))
                        exercise.recalculateReps()
                        exercise.recalculateWeight()
                    }){
                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)){
                        Box(modifier = Modifier
                            .size(12.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color(57, 142, 225, 255))){
                            Icon(imageVector = Icons.Default.Add,
                                contentDescription = "",
                                tint = Color.White)
                        }
                        Text(text = "Add Set",
                            color = Color(57,142,225,255))
                    }
                }
            }
        }
    }
}


@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier
) {
    for(i in 0..<exercises.size){
        isOpened.add(false)
    }
    LazyColumn(
        Modifier
            .clickable {
                for (i in 0..<isOpened.size) {
                    isOpened[i] = false
                }
            }
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        itemsIndexed(
            exercises
        ) { i, exercise ->
            Set(i, exercise)
        }
    }
}