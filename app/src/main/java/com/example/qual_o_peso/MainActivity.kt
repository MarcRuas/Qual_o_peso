package com.example.qual_o_peso

import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pix
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qual_o_peso.ui.theme.Qual_o_pesoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    Scaffold (
        topBar = {  
            MainTop()
        },
        bottomBar = {
            MainBotton()
        }
    ){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTop() {
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
                  IconButton(onClick = { /*TODO*/ }) {
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
    var bottomStates by remember{
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