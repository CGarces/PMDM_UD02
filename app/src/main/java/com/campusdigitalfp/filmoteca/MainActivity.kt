package com.campusdigitalfp.filmoteca

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                    AboutScreen(author = "Carlos Garcés")
                }
            }
        }

@Composable
fun AboutScreen(author: String) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement  = Arrangement.Center) {
            Text(
                text = stringResource(R.string.creada_por, author),
                fontSize = MaterialTheme.typography.titleLarge.fontSize, // Tamaño de la fuente.
                fontWeight = FontWeight.Bold, // Estilo de la fuente en negrita.
                textAlign = TextAlign.Center, // Alineación del texto en el centro.
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible.
                    .padding(16.dp) // Aplica un padding de 16 dp alrededor del texto.
            )

            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = stringResource(R.string.perfil_desarrollador),
                modifier = Modifier
                    .size(150.dp)
            )

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    showToast(
                        context,
                        message =  context.getString(R.string.func_na)
                    )
                }) {
                    Text(stringResource(R.string.ir_web))
                }
                Button(onClick = {
                    showToast(
                        context,
                        message = context.getString(R.string.func_na)
                    )
                }) {
                    Text(stringResource(R.string.obtener_soporte))
                }
            }
            Button(onClick = { showToast(context, message =  context.getString(R.string.func_na)) }) {
                Text(stringResource(R.string.volver))
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
