package com.example.medapp
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
class RespEmergencies {
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
                item {
                    //title
                    Text(
                        "Emergencies",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )

                    //newborn
                    Button(
                        onClick = {
                            navController.navigate("Triage")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text("Resuscitation of the Newborn")
                    }

                    //young infants
                    Button(
                        onClick = {
                            navController.navigate("Triage")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text("Resuscitation of the Young Infant")
                    }

                    //breathing difficulties
                    Button(
                        onClick = {
                            navController.navigate("Triage")
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text("Breathing Difficulties in Newborns and Young Infants")
                    }


                }
            }
        }
    }
}