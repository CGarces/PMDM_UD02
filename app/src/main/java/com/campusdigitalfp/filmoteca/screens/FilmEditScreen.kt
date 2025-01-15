package com.campusdigitalfp.filmoteca.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun
import com.campusdigitalfp.filmoteca.common.FilmDataSource

@Preview(showBackground = true)
@Composable
fun PreviewFilmEditScreen() {
    FilmEditScreen(rememberNavController(), idPelicula = 0  )
}

@Composable
fun FilmEditScreen(navController: NavHostController, idPelicula: Int)   {
    Log.d("PMDM2", "Iniciando la edicion de una pelicula.")
    val film = FilmDataSource.films[idPelicula]

    var titulo by remember { mutableStateOf(film.title) }
    var director by remember { mutableStateOf(film.director) }
    var anyo by remember { mutableIntStateOf(film.year) }
    var url by remember { mutableStateOf(film.imdbUrl) }
    val imagen by remember { mutableIntStateOf(film.imageResId) }
    var comentarios by remember { mutableStateOf(film.comments) }

    var expandedGenero by remember { mutableStateOf(false) }
    var expandedFormato by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val generoList = context.resources.getStringArray(R.array.genero_list).toList()
    val formatoList = context.resources.getStringArray(R.array.formato_list).toList()

    var genero by remember { mutableIntStateOf(film.genre) }
    var formato by remember { mutableIntStateOf(film.format) }


    Scaffold(topBar = { BarraSuperiorComun(navController, cancelar = true) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = imagen),
                        contentDescription = stringResource(R.string.cartel),
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Button(onClick = { navController.popBackStack() }) {
                        Text(stringResource(R.string.tomar_foto))
                    }
                    Button(onClick = { navController.popBackStack() }) {
                        Text(stringResource(R.string.sel_foto))
                    }
                }
                TextField(
                    value = titulo,
                    onValueChange = { newText -> titulo = newText },
                    label = { Text(stringResource(R.string.titulo).plus(":")) },
                    placeholder = { Text(stringResource(R.string.titulo)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(
                    value = director,
                    onValueChange = { newText -> director = newText },
                    label = { Text(stringResource(R.string.director).plus(":")) },
                    placeholder = { Text(stringResource(R.string.director)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(
                    value = anyo.toString(),
                    onValueChange = { newText -> anyo = newText.toInt() },
                    label = { Text(stringResource(R.string.ano).plus(":")) },
                    placeholder = { Text(stringResource(R.string.ano)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(stringResource(R.string.genero), modifier = Modifier.padding(end = 16.dp))

                    Text(generoList[genero], modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedGenero = true }
                    )

                    DropdownMenu(
                        expanded = expandedGenero,
                        onDismissRequest = { expandedGenero = false }
                    ) {
                        generoList.indices.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(generoList[option]) },
                                onClick = {
                                    genero = option
                                    expandedGenero = false
                                }
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(stringResource(R.string.formato), modifier = Modifier.padding(end = 16.dp))

                    Text(formatoList[formato], modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedFormato = true }
                    )

                    DropdownMenu(
                        expanded = expandedFormato,
                        onDismissRequest = { expandedFormato = false }
                    ) {
                        formatoList.indices.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(formatoList[option]) },
                                onClick = {
                                    formato = option
                                    expandedFormato = false
                                }
                            )
                        }
                    }
                }
                TextField(
                    value = url ?: "https://imdb.com",
                    onValueChange = { newText -> url = newText },
                    label = { Text(stringResource(R.string.imdb_url)) },
                    placeholder = { Text(stringResource(R.string.imdb_url)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(
                    value = comentarios?: "Sin comentarios",
                    onValueChange = { newText -> comentarios = newText },
                    label = { Text(stringResource(R.string.comentarios)) },
                    placeholder = { Text(stringResource(R.string.comentarios)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Button(onClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "result",
                            "RESULT_OK"
                        )
                        FilmDataSource.films[idPelicula].year = anyo
                        FilmDataSource.films[idPelicula].format = formato
                        FilmDataSource.films[idPelicula].genre = genero
                        FilmDataSource.films[idPelicula].title = titulo
                        FilmDataSource.films[idPelicula].comments = comentarios
                        FilmDataSource.films[idPelicula].imdbUrl = url
                        Log.d("PMDM2", "Editada pelicula correctamente.")
                        navController.navigate("view/".plus(idPelicula))
                    }) {
                        Text(stringResource(R.string.guardar))
                    }
                    Button(onClick = {
                        Log.d("PMDM2", "Cancelada edicion pelicula.")
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "result",
                            "RESULT_CANCELED"
                        )
                        navController.popBackStack()
                    }) {
                        Text(stringResource(R.string.cancelar))
                    }

                }
            }
        }
    )
}
