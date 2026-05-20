package com.henrryorozco.calculadorapropinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.henrryorozco.calculadorapropinas.ui.theme.CalculadoraPropinasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraPropinasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculadoraPropinasApp()
                }
            }
        }
    }
}

@Composable
fun CalculadoraPropinasApp() {
    var montoCuenta by remember { mutableStateOf("") }
    var porcentajePropina by remember { mutableStateOf("") }
    var resultadoPropina by remember { mutableStateOf("") }
    var resultadoTotal by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Propinas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = montoCuenta,
            onValueChange = { montoCuenta = it },
            label = { Text("Monto de la cuenta ($)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = porcentajePropina,
            onValueChange = { porcentajePropina = it },
            label = { Text("Porcentaje de propina (%)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "O selecciona un porcentaje:", fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("10", "15", "20").forEach { pct ->
                Button(onClick = { porcentajePropina = pct }) {
                    Text("$pct%")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val monto = montoCuenta.toDoubleOrNull() ?: 0.0
                val porcentaje = porcentajePropina.toDoubleOrNull() ?: 0.0
                val propina = monto * porcentaje / 100
                val total = monto + propina
                resultadoPropina = "Propina: ${"%.2f".format(propina)}"
                resultadoTotal = "Total: ${"%.2f".format(total)}"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Propina")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (resultadoPropina.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = resultadoPropina,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = resultadoTotal,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}