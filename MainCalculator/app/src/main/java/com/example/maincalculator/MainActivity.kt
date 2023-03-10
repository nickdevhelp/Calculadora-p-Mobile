package com.example.maincalculator

import android.hardware.lights.Light
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maincalculator.ui.theme.MainCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatoraScreen()
        }
    }
}
@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(onClick = onClick, modifier = modifier,
        interactionSource = interactionSource) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
    }
}


@Composable
fun CalculatoraScreen(){
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current


    Column(Modifier.padding(16.dp)) {
        TextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text(text = "Valor 1")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text(text = "Valor 2")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {
            Button(onClick = {operator = "+"}, modifier = Modifier.padding(5.dp), ){
                Text(text = "+")
            }
            Button(onClick = { operator = "-"}, modifier = Modifier.padding(5.dp)){
                Text(text = "-")
            }
            Button(onClick = { operator = "*"}, modifier = Modifier.padding(5.dp)){
                Text(text = "*")
            }
            Button(onClick = { operator = "/"}, modifier = Modifier.padding(5.dp)){
                Text(text = "/")
            }
            Button(onClick = { operator = "%"}, modifier = Modifier.padding(5.dp)){
                Text(text = "%")
            }
        }

        Row(
            Modifier
                .padding(vertical = 10.dp)
                .align(CenterHorizontally)) {
            Button(modifier = Modifier.padding(5.dp),onClick = {
                if(value1.isNotEmpty() && value2.isNotEmpty() && operator.isNotEmpty()){
                    result = when (operator){
                        "+" -> (value1.toDouble() + value2.toDouble()). toString()
                        "-" -> (value1.toDouble() - value2.toDouble()). toString()
                        "*" -> (value1.toDouble() * value2.toDouble()). toString()
                        "/" -> (value1.toDouble() / value2.toDouble()). toString()
                        "%" -> (value1.toDouble() % value2.toDouble()). toString()
                        else -> ""
                    }
                }
            }) {
                Text(text = "=")
            }
            Button(modifier = Modifier.padding(5.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),onClick = {
                value1 = ""
                value2 = ""
                operator = ""
                result = ""
            }) {
                Text(text = "Limpar")
            }
        }

            if (result.isNotEmpty()){
                //Text(text = "RESULTADO: $result", Modifier.padding(vertical = 16.dp).align(CenterHorizontally).size(100.dp))
                Button(modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(CenterHorizontally), onClick = {

                }) {
                    Text(text = "RESULTADO DA $operator : $result")
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatoraScreen()
}