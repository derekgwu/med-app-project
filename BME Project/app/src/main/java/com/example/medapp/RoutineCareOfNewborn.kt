package com.example.medapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class RoutineCareOfNewborn {
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
                        "Routine Care Of Newborn",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )

                    Objectives()
                    Main(navController)






                }
            }
        }
    }


    //objectives
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
                append("Provide routine care for every newborn.\n")
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("• ")
                }
                append("Offer relevant and timely information, advice and support to caregivers.\n")
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("• ")
                }
                append("Identify newborns with danger signs and those who need special care.\n")

            }
            Text(text)

        }
    }


    //Main text
    @Composable
    private fun Main(navController: NavController){

        Column(
            Modifier.padding(10.dp)
        ){
            Divider(color = Color.Blue, thickness = 1.dp)
            val heading = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)){
                    append("Why is care of the normal term infant so important?")
                }
            }
            Text(heading)
            /*
            Button(
                onClick = {
                    navController.navigate("Triage")
                },
                modifier = Modifier
                    .width(400.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                Text("Triage")
            }
            */
             val para1 = buildAnnotatedString {
                 withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                     append("The vast majority of newborns require no intervention at birth other than routine normal care. ")

                 }
                 append("If this is done well, it vastly reduces the likelihood of problems. " +
                         "Most newborns should be transferred to the post-natal wards for rooming-in with their mothers. " +
                         "These babies still need to be monitored because they are at continued risk of hypothermia and feeding difficulties during " +
                         "the first few days of life. These babies can also become sick and develop danger signs. The mother needs counselling and " +
                         "appropriate " + "treatment when required. Babies born in health facilities should not be sent home in the crucial first " +
                         "48 hours of life. Follow-up care should be organised before the mothers are discharged (also see the chapter on discharge " +
                         "from the hospital. Take time to counsel her and involve another guardian, where possible. Ensure that they understand the " +
                         "implications of leaving early, advise them on danger signs, and arrange a follow-up plan for the baby.")
             }
            Text(para1)
            Text("\n")
            val para2 = buildAnnotatedString {
                append("A post-natal room should be kept warm with no draughts from open doors or windows. " +
                        "A room temperature of at least 25oC is required to help keep the baby warm. A mother and her " +
                        "baby should be kept together from birth if possible. This helps the mother form an early, close-loving relationship (bonding) " +
                        "with her baby. She can also respond quickly when her baby wants to feed, which helps establish breastfeeding and reduces breastfeeding " +
                        "difficulties (1).")
            }
            Text(para2)
            val heading2 = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)){
                    append("Daily Routine Care of the Newborn")
                }
            }
            Text("\n")

            Text(heading2, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            val subheading1 = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("1. Review labour and birth record\n")
                }
            }
            Text(subheading1)

        }
    }


    //bullet points
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







}