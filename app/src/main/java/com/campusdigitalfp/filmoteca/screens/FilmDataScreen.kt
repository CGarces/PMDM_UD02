package com.campusdigitalfp.filmoteca.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@Composable
fun FilmDataScreen(navController: NavHostController, pelicula: String)   {
    val context = LocalContext.current
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    if (savedStateHandle != null) {
        val result by savedStateHandle.getLiveData<String>("result").observeAsState()
        LaunchedEffect(result) {
            if (result != null)
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(topBar = { BarraSuperiorComun(navController) },
        content = { paddingValues ->
            Box(
                Modifier.padding(paddingValues).fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement  = Arrangement.Center) {
                    Text(
                        text = stringResource(R.string.datos_pelicula,pelicula),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize, // Tamaño de la fuente.
                        fontWeight = FontWeight.Bold, // Estilo de la fuente en negrita.
                        textAlign = TextAlign.Center, // Alineación del texto en el centro.
                        modifier = Modifier
                            .fillMaxWidth() // Ocupa todo el ancho disponible.
                            .padding(16.dp) // Aplica un padding de 16 dp alrededor del texto.
                    )


                    Button(onClick = { navController.navigate("view/Dummy") }) {
                        Text(stringResource(R.string.ver_relacion))
                    }
                    Button(onClick = {
                        navController.navigate("edit")
                    }) {
                        Text(stringResource(R.string.editar))
                    }
                    Button(onClick = { navController.popBackStack("list", false) }) {
                        Text(stringResource(R.string.volver_principal))
                    }
                }
            }
        }
    )
}
