//jaundice class 
package com.example.medapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.compose.rememberNavController

// Jaundice page class
class Jaundice{
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
                        "Jaundice and Phototherapy",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )

                    Objectives()
                    /*
                    Introduction()

                     */
                    SymptomsandCausesTable()
                    /*
                    HistoryTable()
                    ExaminationTable()

                     */
                    SeverityOfJaundice()
                    /*
                    Investigations()
                    Treatment()
                    PreventionOfJaundice()
                    */

                    //PrioritiesTable()
                    /*
                    PhototherapyLights()
                    PhototherapyLightsPage() //goes to another page
                    PhototherapyMusts()
                    ProlongedJaundice()

                     */


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
            append("Describe physiological and pathological jaundice , including prolonged jaundice.\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Describe how to evaluate jaundice including assess its severity.\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Describe the management of jaundiced NYI\n")
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
        }
        Text(text)
    }
}

@Composable
private fun Main(navController: NavController) {
    Column(
        Modifier.padding(10.dp)
    ) {
        Divider(color = Color.Blue, thickness = 1.dp)
        val heading = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                append("Introduction")
            }
        }
        Text(heading)

        val para1 = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("More than 50% of normal newborns and 80% of preterm infants become jaundiced and the healthcare " + "worker needs to be familiar with diagnosis and management. The number of newborns requiring " + "treatment for jaundice can be reduced by keeping the baby with the mother allowing for " + "more frequent breastfeeding and increasing bilirubin excretion in the stools.")
            }
        }

        Text(para1)
    }
}


@Composable
private fun SeverityOfJaundice() {

    Column(
        Modifier.padding(10.dp)
    )
    {
        Divider(color = Color.Black, thickness = 1.dp)
        Text(
            "Assessment of Severity of Jaundice",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            )
        Text(
            "Assess the level of jaundice clinically: blanching reveals the  " + "underlying colour. Neonatal jaundicefirst becomes visible on the face and forehead " + "and gradually becomes visible on the trunk and extremities. This can be " + "used to decide clinically when the baby should be treated. The Kramer scale " + "is used to clinically assess the severity of jaundice depending on the age in " + "days and maturity of the baby. It is not as accurate as a serum bilirubin level or " + "transcutaneous bilirubin reading. If possible, confirm with a transcutaneous " + "bilirubinometer or a serum bilirubin. The bilirubinometer is used to measure " + "serum bilirubin and much more accurate than a clinical assessment. \n" + "Also assess for: features of acute bilirubin encephalopathy (kernicterus) , and " + "dehydration with which jaundice is commonly associated.",
            //fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            textAlign = TextAlign.Left

        )

    }
}
@Composable
fun SymptomsandCausesTable(){
    val columnWeight = .5f // 30%
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp))
    {
        Row(Modifier.fillMaxSize()) {
            TableCell(text = "Physiological", weight = columnWeight, height=120.dp,color = Color.Black)
            TableCell(text = "Pathological", weight = 0.5f, height=120.dp, color=Color.Black)
            TableCell(text = "Prolonged/ pathological", weight = columnWeight, height=120.dp,color = Color.Black)
        }

        Row(Modifier.fillMaxSize()) {
            TableCell(text = "Appears after 48 hours of life. Maximum by 4th and "+"7th day. Generally, disappears without any treatment "+ "but some NYI will require phototherapy.", weight = columnWeight, height=120.dp, color = Color.Black)
            TableCell(text = "Starting on the first day of life; may have fever. Deep jaundice: palms and soles.", weight = 0.5f, height=120.dp, color = Color.Black)
            TableCell(text = "Jaundice lasting for longer than 14 days in term infants and 21 days in preterm infants.", weight = columnWeight, height=120.dp,color = Color.Black)

        }

        Row(Modifier.fillMaxSize()){
            TableCell(text=" ", weight = columnWeight, height=120.dp, color = Color.Black)
            TableCell(text="Possible causes: Haemolysis, Neonatal sepsis, Congenital infection", weight=columnWeight, height=120.dp, color=Color.Black)
            TableCell(text = "Possible causes: Hypothyroidism, Neonatal hepatitis. Biliary atresia (pale stool and dark yellow urine)", weight = columnWeight, height=120.dp,color = Color.Black)

        }
    }
}
