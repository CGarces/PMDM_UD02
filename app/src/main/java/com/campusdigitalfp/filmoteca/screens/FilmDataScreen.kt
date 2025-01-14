package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@Preview(showBackground = true)
@Composable
fun PreviewFilmDataScreen() {
    FilmDataScreen(rememberNavController(),  pelicula= "Harry Potter y la piedra Filosofal" )
}

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
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.cartel),
                        contentDescription = stringResource(R.string.perfil_desarrollador),
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Column  {
                        Text(
                            text = pelicula,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.director).plus(":"),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Chris Columbus",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stringResource(R.string.ano).plus(":"),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "2021",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Blueray, SCi-Fi",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Button(
                    onClick = {
                        abrirIMDB(context = context)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.ver_imdb))
                }
                Text(
                    text = "Version Extendida",
                    modifier = Modifier.fillMaxWidth() ,
                )
                Row {
                    Button(onClick = { navController.popBackStack("list", false) }) {
                        Text(stringResource(R.string.volver))
                    }
                    Button(onClick = {
                        navController.navigate("edit")
                    }) {
                        Text(stringResource(R.string.editar))
                    }
                }
            }
        }
    )
}

fun abrirIMDB(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://www.imdb.com/title/tt0241527/") // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}
