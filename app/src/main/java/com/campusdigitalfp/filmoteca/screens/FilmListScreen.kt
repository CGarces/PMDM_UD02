package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun
import com.campusdigitalfp.filmoteca.common.Film
import com.campusdigitalfp.filmoteca.common.FilmDataSource.films

@Preview(showBackground = true)
@Composable
fun PreviewFilmListScreen() {
    FilmListScreen(rememberNavController() )
}

@Composable
fun FilmListScreen(navController: NavHostController)   {
    val isActionMode = remember { mutableStateOf(false) }
    val selectedFilms = remember { mutableStateListOf<Film>() }

    Scaffold(topBar = { BarraSuperiorComun(navController, main = true, isActionMode = isActionMode, selectedFilms =selectedFilms) },
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn {
                    items(films) { film ->
                        DrawFilm(
                            film = film,
                            onClick = {
                                if (isActionMode.value) {
                                    // Añade o elimina de la selección
                                    if (selectedFilms.contains(film)) {
                                        selectedFilms.remove(film)
                                        isActionMode.value = !selectedFilms.isEmpty()
                                    } else {
                                        selectedFilms.add(film)
                                    }
                                } else {
                                    navController.navigate("view/${film.id}")
                                }
                            },
                            //Activa el modo edición y selecciona el hábito en el caso de una pulsación larga
                            onLongClick = {
                                isActionMode.value = true
                                selectedFilms.add(film)
                            },
                            isSelected = selectedFilms.contains(film)

                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DrawFilm(
    film: Film,
    onClick: () -> Unit,
    onLongClick: () -> Unit, isSelected: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Borrar seleccionados"
            )
        } else {
            Image(
                painter = painterResource(film.imageResId),
                contentDescription = stringResource(R.string.cartel),
                modifier = Modifier
                    .size(90.dp)
            )
        }
        Column {
            Text(
                text = film.title,
                style = MaterialTheme.typography.titleLarge,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.director).plus(":"),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = film.director,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

