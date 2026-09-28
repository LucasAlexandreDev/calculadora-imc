package com.example.calculadora_imc

import android.R.attr.contentDescription
import android.R.attr.text
import android.R.attr.y
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora_imc.ui.theme.CalculadoraimcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraimcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    CarregarTelaImc(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun calcularImc(modifier: Modifier = Modifier): Double {


    var peso: Double by remember {
        mutableStateOf(0.0)
    }

    var altura: Double by remember {
        mutableStateOf(0.0)
    }

    var result = peso / (altura * altura)
    return result
}

@Composable
fun CarregarTelaImc(modifier: Modifier= Modifier){

    Column(modifier = modifier
        .fillMaxSize()
    ) {

            // ---| header |---
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(160.dp)
                    .background(color = colorResource(id = R.color.cor_app_imc)),

                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Image(
                    painter = painterResource(R.drawable.logo_calculadora_imc),
                    contentDescription = "Logo do App de IMC",

                    modifier = Modifier
                        .size(80.dp)
                        .padding(vertical = 16.dp)

                )

                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // ---| formulário |---
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                ) {

                Card(modifier= Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-30).dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F6F6)
                    ),

                    elevation = CardDefaults.cardElevation(4.dp),

                ) {

                }

            }

            // ---| card result |---

        }

    }