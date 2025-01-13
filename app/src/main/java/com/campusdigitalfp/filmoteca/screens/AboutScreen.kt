package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@Composable
fun AboutScreen(navController: NavHostController)  {
    val context = LocalContext.current
    val author = "Carlos Garcés"
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
                    abrirPaginaWeb("https://github.com/CGarces/PMDM_UD02", context = context)
                }) {
                    Text(stringResource(R.string.ir_web))
                }
                Button(onClick = {
                    mandarEmail(context = context, email = "cgarcesba@campusdigitalfp.es", asunto =  context.getString(
                        R.string.incidencia_con_filmoteca))
                }) {
                    Text(stringResource(R.string.obtener_soporte))
                }
            }
            Button(onClick = { navController.popBackStack() }) {
                Text(stringResource(R.string.volver))
            }
        }
    }
}

fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}

fun mandarEmail(context: Context, email: String, asunto: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
        putExtra(Intent.EXTRA_SUBJECT, asunto)
    }

    // Verifica si hay una aplicación que puede manejar el Intent
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}