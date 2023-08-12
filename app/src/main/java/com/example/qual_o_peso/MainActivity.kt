package com.example.qual_o_peso


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Pix
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.qual_o_peso.ui.theme.Qual_o_pesoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            Qual_o_pesoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

class MainContentState {
    var peso by mutableStateOf("")
    var altura by mutableStateOf("")
    var resultado by mutableStateOf("")
    fun reset() {
        peso = ""
        altura = ""
        resultado = ""
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val mainContentState = remember { MainContentState() }
    Scaffold (
        topBar = {
            MainTop(mainContentState)
        }
    ){

    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        MainTextField(mainContentState)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(mainContentState: MainContentState) {

    TextField(value = mainContentState.peso, onValueChange = { newValue -> mainContentState.peso = newValue },
        modifier = Modifier
            .width(250.dp)
            .padding(bottom = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
        placeholder = {
            Text(text = "Peso",
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.DarkGray,
            cursorColor = Color.Blue,
            focusedBorderColor = Color(0xFFFEDBD0),
            containerColor = Color(0xFFFEDBD0),
            unfocusedBorderColor = Color(0xFFFEDBD0)
        )
    )
    TextField(value = mainContentState.altura, onValueChange = { newValue -> mainContentState.altura = newValue },
        modifier = Modifier
            .width(250.dp)
            .padding(bottom = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
        placeholder = {
            Text(text = "Altura",
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.DarkGray,
            cursorColor = Color.Blue,
            focusedBorderColor = Color(0xFFFEDBD0),
            containerColor = Color(0xFFFEDBD0),
            unfocusedBorderColor = Color(0xFFFEDBD0)
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
        val pesoValue = mainContentState.peso.toDoubleOrNull()
        val alturaValue = mainContentState.altura.toDoubleOrNull()

        if (pesoValue != null && alturaValue != null){
            val calculo = pesoValue / (alturaValue * alturaValue)
            val resultadoFormatado = String.format("%.2f", calculo)
            val status: String = when {
                calculo < 18.5 -> "Abaixo do peso"
                calculo < 24.9 -> "Peso normal"
                calculo < 29.9 -> "Sobrepeso"
                else -> "Obesidade"
            }
            mainContentState.resultado = "O seu IMC é $resultadoFormatado - $status"
        }
        else{
            mainContentState.resultado = "Valores inválidos"
        }

    },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFEDBD0),
            contentColor = Color.DarkGray
        )
    ) {
        Text(text = "Calcular IMC")

    }
    Text(text = mainContentState.resultado)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTop(mainContentState: MainContentState) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()){
                Text(text = "IMC")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFFFEDBD0),
        ),
        actions = {
                  IconButton(onClick = {mainContentState.reset()}) {
                      Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                  }
        },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp))

    )
}
@Composable
fun MainBotton() {
    BottomAppBar(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .height(70.dp),
        containerColor = Color(0xFFFEDBD0),
    ){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "IMC")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Pix, contentDescription = "Libra")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Water")
        }

    }
}

@Composable
fun MainNav() {
    val bottomStates by remember{
        mutableStateOf("Home")
    }
    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .height(70.dp),
        containerColor = Color(0xFFFEDBD0),
    ){
        NavigationBarItem(selected = bottomStates == "Home", onClick = { /*TODO*/ }, icon = { Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = null
        )})
        NavigationBarItem(selected = bottomStates == "Home", label = { Text(text = "IMC")},onClick = { /*TODO*/ }, icon = { Icon(
            imageVector = Icons.Filled.Fastfood,
            contentDescription = null,

        )})
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Qual_o_pesoTheme {
        MainContent()
    }
}