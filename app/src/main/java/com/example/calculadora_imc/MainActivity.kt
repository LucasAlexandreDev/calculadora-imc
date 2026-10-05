package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora_imc.R
import com.example.calculadora_imc.ui.theme.CalculadoraimcTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CalculadoraimcTheme() {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    TelaPrincipal(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun TelaPrincipal(modifier: Modifier = Modifier) {

    var alturaUsuario by remember { mutableStateOf("") }

    var pesoUsuario by remember { mutableStateOf("") }

    var resultadoImc by remember { mutableStateOf(0.0) }

    var categoriaImc by remember { mutableStateOf("---") }

    // O card começa cinza antes de qualquer cálculo.
    var corCategoria by remember {
        mutableStateOf(Color.Gray)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        // Cabeçalho da calculadora
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app_imc)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.logo_calculadora_imc),
                contentDescription = "Calculadora de IMC",
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

        // Área onde o usuário informa seus dados
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        modifier = Modifier.padding(vertical = 32.dp),
                        text = "Seus dados",
                        fontSize = 24.sp,
                        color = colorResource(R.color.cor_app_imc),
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        modifier = Modifier,
                        value = alturaUsuario,
                        onValueChange = { alturaUsuario = it },
                        label = {
                            Text(text = "Altura")
                        },
                        placeholder = {
                            Text(text = "Digite sua altura")
                        },
                        shape = RoundedCornerShape(size = 12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(R.color.cor_app_imc),
                            unfocusedBorderColor = colorResource(R.color.cor_app_imc)
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier,
                        value = pesoUsuario,
                        onValueChange = { pesoUsuario = it },
                        label = {
                            Text(text = "Peso")
                        },
                        placeholder = {
                            Text(text = "Digite seu peso")
                        },
                        shape = RoundedCornerShape(size = 12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(R.color.cor_app_imc),
                            unfocusedBorderColor = colorResource(R.color.cor_app_imc)
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    // Botões de calcular e limpar
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {

                        Button(
                            onClick = {

                                if (
                                    pesoUsuario.toDouble() >= 0.0 &&
                                    alturaUsuario.toDouble() >= 0.0
                                ) {

                                    resultadoImc = CalcularImc(
                                        alturaUsuario.toDouble(),
                                        pesoUsuario.toDouble()
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.cor_app_imc)
                            )
                        ) {

                            Text(text = "Calcular")
                        }

                        Button(
                            onClick = {

                                alturaUsuario = ""
                                pesoUsuario = ""
                                resultadoImc = 0.00
                                categoriaImc = "---"

                                // Volta o card para a cor inicial.
                                corCategoria = Color.LightGray
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray
                            )
                        ) {

                            Text(text = "Limpar")
                        }
                    }
                }
            }
        }

        val imcFormatado = String.format("%.2f", resultadoImc)

        // A função retorna a categoria
        var resultadoCategoria = ClassificarImc(resultadoImc)

        // A função retorna a cor da categoria
        corCategoria = ClassificarCor(resultadoImc)

        // Card que apresenta o resultado do IMC
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(65.dp)
                .offset(y = (30).dp),
            colors = CardDefaults.cardColors(
                containerColor = corCategoria
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = if (resultadoImc == 0.00) "---" else imcFormatado,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = resultadoCategoria,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Calcula o IMC do usuário.
fun CalcularImc(alturaUsuario: Double, pesoUsuario: Double): Double {

    var resultado = pesoUsuario / (alturaUsuario * alturaUsuario)
    return resultado
}

// Classifica o IMC e retorna a categoria
fun ClassificarImc(valorImc: Double): String{

    var categoria = ""

    if (valorImc < 18.5) {
        categoria = "Abaixo do peso"

    } else if (valorImc >= 18.5 && valorImc < 25) {
        categoria = "Peso ideal"

    } else if (valorImc >= 25 && valorImc < 30) {
        categoria = "Levemente acima do peso"

    } else if (valorImc >= 30 && valorImc < 35) {
        categoria = "Obesidade grau I"

    } else if (valorImc >= 35 && valorImc < 40) {
        categoria = "Obesidade grau II"

    } else {
        categoria = "Obesidade grau III"
    }

    return categoria
}

@Composable
fun ClassificarCor(valorImc: Double): Color {

    var cor: Color

     if (valorImc < 18.5 || valorImc >= 30) {
        cor = colorResource(R.color.classificacao_vermelho)

    } else if (valorImc < 25) {
        cor = colorResource(R.color.classificacao_verde)

    } else {
        cor = colorResource(R.color.classificacao_laranja)
    }

    return cor
}