package com.example.medapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class SelectionPage {

    @Composable
    fun Content(navController: NavController) {
        ContentImpl(navController)
    }

    @Composable
    private fun ContentImpl(navController: NavController) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Text(
                        "Care of the Infant and Newborn (COIN) in Malawi 2022",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(24.dp),
                        textAlign = TextAlign.Center

                    )

                }
                item{
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
                }

                item{
                    Button(
                        onClick = {
                            navController.navigate("RespEmergency")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text("Emergencies (Resuscitation, Breathing (oxygen, CPAP), Shock, Coma/ Convulsions and Hypoglycaemia)")
                    }
                }

                item{
                    Button(
                        onClick = {
                            navController.navigate("RoutineCareOfNewborn")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text("Routine Care of Newborn Infant")
                    }
                }

                item{
                    Text("IN PROGRESS")
                }
                item{
                    Button(
                        onClick = {
                            navController.navigate("Jaundice")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text( "Jaundice and Phototherapy")
                    }
                }

                item{
                    Text("TODO")
                }
                //button labels
                val btn_labels = arrayOf(
                    "Birth Asphyxia/Hypoxic Ischaemic Encephalopathy (HIE)",
                    "LBW/Preterm incl Hypothermia",

                    "Infections (Sepsis and Meningitis) incl Infection Prevention",
                    "Fluid management and diarrhoea/dehydration",
                    "Equipment",
                    "Formulary",
                    "Wall Charts",
                    "Referral, Transport and Discharge",
                    "Quality Improvement, Death Audits and Good clinical Pracitice",
                    "Appendices (CCP, admission, audit and refereal forms) and Bibliography",
                    "Background, Accronyms, Abbreviations and Introduction"
                )

                items(btn_labels) { label ->
                    Button(
                        onClick = {
                            navController.navigate("Triage")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text(label)
                    }
                }



            }
        }
    }

}
