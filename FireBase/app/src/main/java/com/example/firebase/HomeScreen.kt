package com.example.firebase
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.database.database


@Composable
fun HomeScreen(modifier: Modifier = Modifier){

val database = Firebase.database
val myRef = database.getReference("message")
    var text by remember { mutableStateOf("")}
    Column(modifier = Modifier.padding(16.dp)){
        TextField(value = text, onValueChange = {newtext -> text =
            newtext},
            label = {Text(text="enter your data")}
        )
        Button(onClick = { myRef.setValue(text) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Submit")
        }
    }
}