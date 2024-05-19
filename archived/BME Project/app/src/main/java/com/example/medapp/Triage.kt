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
                    Emergencies()
                    AirwayEmergencyTable()
                    CirculationEmergencyTable()
                    ComaEmergencyTable()
                    DehydrationEmergencyTable()
                    Priorities()
                    PrioritiesTable()


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
        val custom_yellow = Color(0xFFFCBA03);
        val priority_text1 = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("• ")
            }
            append("Those who are less sick must be seen next and are")
            withStyle(style = SpanStyle(color = custom_yellow)){
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
fun TriagePriorityTable() {
    val columnWeight = .5f // 30%
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp))
            {
            val custom_yellow = Color(0xFFFCBA03);
            Row(Modifier.fillMaxSize()) {
                TableCell(text = "EMERGENCY [E]", weight = columnWeight, height=120.dp,color = Color.Red)
                TableCell(text = "Patient must be seen at once may need life-saving treatment", weight = 0.5f, height=120.dp, color=Color.Red)
            }

            Row(Modifier.fillMaxSize()) {
                TableCell(text = "PRIORITY [P]", weight = columnWeight, height=120.dp, color = custom_yellow)
                TableCell(text = "patient needs rapid assessment needs to be seen soon", weight = 0.5f, height=120.dp, color = custom_yellow)
            }

            Row(Modifier.fillMaxSize()){
                TableCell(text="NON-URGENT* [Q]", weight = columnWeight, height=120.dp, color = Color.Green)
                TableCell(text="patient can safely wait to be seen NYI are never in this category", weight=columnWeight, height=120.dp, color=Color.Green)
            }
    }
}
@Composable
fun Emergencies(){
    Column(Modifier.padding(10.dp)){
        Divider(color = Color.Blue, thickness = 1.dp)
        val heading = buildAnnotatedString {
            withStyle(style = SpanStyle(Color.Red)){
                append("EMERGENCIES")
            }
        }
        Text(heading, textAlign = TextAlign.Left)
        Text("Emergencies are sent straight to the best place for resuscitation.",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold
        )
        val paragraph1 = buildAnnotatedString {
            append("The")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(" ABCCCD ")
            }
            append("concept is used to identify emergencies. " +
                    "This is a logical and quick way of identifying how sick a " +
                    "child is; it does not take the place of a thorough examination " +
                    "to make a diagnosis but is a screening tool to identify problems that " +
                    "require immediate attention.")
        }
        Text(paragraph1,
            textAlign = TextAlign.Left)
        Text("\nFor triage, we need to know:",
            textAlign = TextAlign.Left)
    }


}

@Composable
fun AirwayEmergencyTable(){
    Column(Modifier.padding(10.dp)) {


        val FirstRowWeight = 0.3f
        val FirstRowHeight = 70.dp
        Row{
            TableCell("", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Black)
            TableCell("Emergency Signs", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
            TableCell("Emergency Treatment", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
        }
        val SecondRowWeight = 0.3f
        val SecondRowHeight = 250.dp
        Row{
            TableCell("Airway and Breathing", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Not breathing,\n" +
                    "Centrally cyanosed,\n" +
                    "Noisy breathing,\n" +
                    "Severe respiratory distress", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Manage the airway\n" +
                    "Give oxygen\n" +
                    "If present, remove foreign body, BMV", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
        }


        Whitespace()
    }
}

@Composable
fun CirculationEmergencyTable(){
    Column(Modifier.padding(10.dp)) {


        val FirstRowWeight = 0.3f
        val FirstRowHeight = 70.dp
        Row{
            TableCell("", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Black)
            TableCell("Emergency Signs", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
            TableCell("Emergency Treatment", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
        }
        val SecondRowHeight = 250.dp
        Row{
            TableCell("Circulation", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Cold hands,\n" +
                    "Capillary refill of > 3secs,\n" +
                    "Weak and fast pulse", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Manage the airway\n" +
                    "Give oxygen\n" +
                    "Start fluids 10ml/Kg IV", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
        }


        Whitespace()
    }
}

@Composable
fun ComaEmergencyTable(){
    Column(Modifier.padding(10.dp)) {


        val FirstRowWeight = 0.3f
        val FirstRowHeight = 70.dp
        Row{
            TableCell("", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Black)
            TableCell("Emergency Signs", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
            TableCell("Emergency Treatment", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
        }
        val SecondRowHeight = 250.dp
        Row{
            TableCell("Coma/ Convulsions", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Unconscious,\n" +
                    "Convulsing,\n" +
                    "Low blood sugar", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Manage the airway,\n" +
                    "Give oxygen,\n" +
                    "Give 10% Dextrose IV,\n" +
                    "Position the baby", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
        }


        Whitespace()
    }
}

@Composable
fun DehydrationEmergencyTable(){
    val navController = rememberNavController()
    Column(Modifier.padding(10.dp)) {


        val FirstRowWeight = 0.3f
        val FirstRowHeight = 70.dp
        Row{
            TableCell("", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Black)
            TableCell("Emergency Signs", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
            TableCell("Emergency Treatment", weight=FirstRowWeight, height=FirstRowHeight, color=Color.Red)
        }
        val SecondRowHeight = 250.dp
        Row{
            TableCell("Dehydration", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("Lethargy,\n" +
                    "Sunken eyes,\n" +
                    "Prolonged skin pinch >2secs", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
            TableCell("No Malnutrition,\n" +
                    "Give IV fluids +NGT,\n" +
                    "Severe Malnutrition,\n" +
                    "Give NGT, try to avoid IV", weight=FirstRowWeight, height=SecondRowHeight, color=Color.Black)
        }
        /*SET UP ROUTEs*/


        Whitespace()
    }
}

@Composable
fun Priorities(){
    Column(Modifier.padding(10.dp)){
        Divider(color = Color.Blue, thickness = 1.dp)
        val custom_yellow = Color(0xFFFCBA03);
        val heading = buildAnnotatedString {
            withStyle(style = SpanStyle(custom_yellow)){
                append("PRIORITIES")
            }
        }
        Text(heading, textAlign = TextAlign.Left)

        Text("Priorities are sent to the front of the queue to be seen quickly.",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold
        )
        val paragraph1 = buildAnnotatedString {
            append("When emergencies have been excluded, " +
                    "signs and symptoms for priority are looked" +
                    " for. Priority signs can be remembered with" +
                    " the letters 3TPR, MOB. Remember that ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("all infants less than 2 months of age are priorities.")
            }
            append(" This is because infants can deteriorate " +
                    "rapidly; they are difficult to assess " +
                    "without a thorough examination; and to prevent " +
                    "them remaining in a queue exposed to infections from " +
                    "other children.")
        }
        Whitespace()
        Text(paragraph1,
            textAlign = TextAlign.Left)


        val subheader = buildAnnotatedString {
            withStyle(style = SpanStyle(custom_yellow)){
                append("Priority Signs")
            }
            append(" are:")
        }
        Whitespace()
        Text(subheader,
            textAlign = TextAlign.Left)
    }

}

@Composable
fun PrioritiesTable(){
    Column(Modifier.padding(10.dp)){
        val custom_yellow = Color(0xFFFCBA03);
        val heading = buildAnnotatedString {
            withStyle(style = SpanStyle()){
                append("3T's")
            }
        }
        Text(heading, textAlign = TextAlign.Left, fontSize = 32.sp)
        val t_bullet_points = arrayOf(
            "Tiny (less than 2 month of age)",
            "Temperature (high temperature as judged by your hand)",
            "Trauma"
        )
        Text(bullet_point(t_bullet_points))

        Text("3P's", textAlign = TextAlign.Left, fontSize = 32.sp)
        val p_bullet_points = arrayOf(
            "Pain",
            "Pallor",
            "Poisoning"
        )
        Text(bullet_point(p_bullet_points))

        Text("3R's", textAlign = TextAlign.Left, fontSize = 32.sp)
        val r_bullet_points = arrayOf(
            "Respiratory distress (not life threatening)",
            "Referral (urgent)",
            "Restless"
        )
        Text(bullet_point(r_bullet_points))
        Text("MOB", textAlign = TextAlign.Left, fontSize = 32.sp)
        val mob_bullet_points = arrayOf(
            "Malnutrition",
            "Oedema",
            "Burns"
        )
        Text(bullet_point(mob_bullet_points))





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


//Table cells
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    height: Dp,
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
            .height(height)
            .padding(8.dp)
    )
}

@Composable
fun Whitespace(){
    Text("\n\n\n")

}

