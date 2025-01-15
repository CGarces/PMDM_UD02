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
import com.campusdigitalfp.filmoteca.common.FilmDataSource

@Preview(showBackground = true)
@Composable
fun PreviewFilmDataScreen() {
    FilmDataScreen(rememberNavController(), idPelicula = 0  )
}

@Composable
fun FilmDataScreen(navController: NavHostController, idPelicula: Int)   {
    val context = LocalContext.current
    val film = FilmDataSource.films[idPelicula]
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val formatoList = context.resources.getStringArray(R.array.formato_list).toList()
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
                        painter = painterResource(id = film.imageResId),
                        contentDescription = stringResource(R.string.cartel),
                        modifier = Modifier.size(150.dp)
                    )
                    Column  {
                        Text(
                            text = film.title,
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
                            text = film.director,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stringResource(R.string.ano).plus(":"),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = film.year.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = formatoList[film.format],
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Button(
                    onClick = {
                        film.imdbUrl?.let { abrirIMDB(context = context, it) }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.ver_imdb))
                }
                Text(
                    text = film.comments ?: "Sin comentarios",
                    modifier = Modifier.fillMaxWidth() ,
                )
                Row {
                    Button(onClick = { navController.popBackStack("list", false) }) {
                        Text(stringResource(R.string.volver))
                    }
                    Button(onClick = {
                        navController.navigate("edit/".plus(film.id))
                    }) {
                        Text(stringResource(R.string.editar))
                    }
                }
            }
        }
    )
}

fun abrirIMDB(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}
