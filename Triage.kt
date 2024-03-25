package com.example.medapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Triage page class
class Triage {
    @Composable
    fun Content(navController: NavController) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),

            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item{
                    //title
                    Text(
                        "Triage",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    Objectives()
                    TriageSorting()
                    TriagePriorityTable()


                }
            }
        }
    }
}

@Composable
private fun Objectives(){
    Column(
        Modifier.padding(10.dp)
    )
    {
        Divider(color = Color.Blue, thickness = 1.dp)
        Text(
            "Learning Objectives",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            "After completion of this session the participant should be able to:",
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            textAlign = TextAlign.Left

        )
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Define Triage\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Describe how to triage\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Understand ABCCCD and priority concept\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Demonstrate triage skills\n")
        }
        Text(text)

    }
}

@Composable
private fun TriageSorting(){
    Column(
        Modifier.padding(10.dp)
    )
    {
        Divider(color = Color.Blue, thickness = 1.dp)
        Text(
            "Triage Means Sorting",
            fontWeight = FontWeight.Bold,
        )
        val bullet_points = arrayOf(
            "Triage is the process of rapidly assessing all sick children when they first arrive in hospital and sorting them according to their need.",
            "Triage assessment can be done anywhere such as in outpatients, or the ward.",
            "Triage should be carried out, on arrival, to all new patients to the hospital.",
            "Triage can be done by any trained person – e.g. a health worker, a receptionist, a guard.",

        )
        Text(bullet_point(bullet_points))
        val emergency_text1 = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Those with life threatening illness must be seen immediately as")
        }
        val emergency_text2 = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(" EMERGENCY")
            }

        }
        val combined = buildAnnotatedString {
            append(emergency_text1)
            append(emergency_text2)
        }
        Text(combined)
        val priority_text1 = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Those who are less sick must be seen next and are")
            withStyle(style = SpanStyle(color = Color.Yellow)){
                append(" PRIORITIES")
            }
        }
        Text(priority_text1)
        val nonurgent = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Those who are NON-URGENT cases can wait their turn in the")
            withStyle(style = SpanStyle(color = Color.Green)){
                append(" QUEUE")
            }

        }
        Text(nonurgent)




    }
}
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    color: Color

) {
    val colored = buildAnnotatedString {
        withStyle(style = SpanStyle(color = color)){
            append(text)
        }
    }

    Text(
        text = colored,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)


    )




}

@Composable
fun TriagePriorityTable() {



    val columnWeight = 1f // 30%


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp))
            {

            Row() {

                TableCell(text = "EMERGENCY [E]", weight = columnWeight, color = Color.Red)

                TableCell(text = "Patient must be seen at once may need life-saving treatment", weight = columnWeight, color=Color.Red)

            }

            Row() {
                TableCell(text = "PRIORITY [P]", weight = columnWeight, color = Color.Yellow)
                TableCell(text = "patient needs rapid assessment needs to be seen soon", weight = columnWeight, color = Color.Yellow)
            }
    }
}

fun bullet_point(labels: Array<String>): AnnotatedString {
    val text = buildAnnotatedString {
        for(label in labels){
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append(label +"\n")
        }
    }
    return text
}
