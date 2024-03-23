package com.example.medapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medapp.ui.theme.MedAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedAppTheme {
                Routes()
                // Display the title screen

            }
        }
    }
}

@Composable
//routes
fun Routes() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

        //welcome page route
        composable("main") {
            TitleScreen(navController)
        }

        //selection page route
        composable("selectionPage") {
            SelectionPage().Content(navController)
        }

        //triage page route
        composable("Triage") {
            Triage().Content(navController)
        }


    }
}


@Composable
//main screen
fun TitleScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display your app's logo or any other image
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )

        // Display the app title
        Text(
            text = "MedApp",
            modifier = Modifier.padding(top = 16.dp)
        )

        // Display a greeting message or any introductory text
        Text(
            text = "Welcome to MedApp!",
            modifier = Modifier.padding(top = 8.dp)
        )

        // Button to navigate to SelectionPage
        Button(
            onClick = {
                navController.navigate("selectionPage")
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Get Started")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleScreenPreview() {
    TitleScreen(rememberNavController())
}

