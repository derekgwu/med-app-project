package com.example.medapp

//import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScope
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
//import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Routine Care of the Normal Newborn Infant class
class RoutineCareoftheNormalNewbornInfant {

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
                        "Routine Care of the Normal Newborn Infant",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    Objectives()
                    CareBackground()
                    //DangerSigns()

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
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,

            )
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Provide routine care for every newborn\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Offer relevant and timely information, advice and support to caregivers\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Identify newborns with danger signs and those who need special care\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            Text(
                "You also find sections on",
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,

                )
            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("• ")
                }
                append("Breastfeeding\n")
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("• ")
                }
                append("Common Congenital Infections (HIV, Tuberculosis, Syphilis)\n")
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("• ")
                }
                append("Discharge and Providing Follow-up care\n")
            }
            Text(text)

        }
    }
}
    @Composable
    private fun CareBackground() {
        Column(
            Modifier.padding(10.dp)
        )
        {
            Divider(color = Color.Blue, thickness = 1.dp)
            Text(
                "Why is care of the normal term infant so important?",
                fontWeight = FontWeight.Bold,
            )
        }
    }