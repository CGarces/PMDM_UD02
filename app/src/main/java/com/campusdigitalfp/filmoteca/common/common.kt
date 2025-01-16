package com.campusdigitalfp.filmoteca.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorComun(
    navController: NavHostController, main: Boolean = false, cancelar: Boolean = false,
    isActionMode: MutableState<Boolean> = mutableStateOf(false),
    selectedFilms: MutableList<Film> = mutableStateListOf<Film>() )
{
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        navigationIcon = {
            if (!main) {
                IconButton(onClick = {
                    if (cancelar)
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "result",
                            "RESULT_CANCELED"
                        )
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.volver)
                    )
                }
            }
        },
        actions = {
            if (isActionMode.value) {
                IconButton(onClick = {
                    FilmDataSource.films.removeAll(selectedFilms)
                    selectedFilms.clear()
                    isActionMode.value = false
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Borrar seleccionados"
                    )
                }
            }
            if (main) {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú desplegable"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            val dummy = Film(id=FilmDataSource.films.size )
                            FilmDataSource.films.add(dummy)
                            expanded = false
                            navController.navigate("list")

                        },
                        text = { Text(stringResource(R.string.add_pelicula)) }
                    )
                    DropdownMenuItem(
                        onClick = {
                            navController.navigate("about")
                        },
                        text = { Text(stringResource(R.string.acerca_de)) }
                    )
                }
            }
        }
    )
}

data class Film(
    var id: Int = 0,
    var imageResId: Int = R.drawable.cartel,
    var title: String = "<Sin título>",
    var director: String = "Desconocido",
    var year: Int = 0,
    var genre: Int = 0,
    var format: Int = 0,
    var imdbUrl: String? = null,
    var comments: String? = null
) {
    override fun toString(): String {
        // Al convertir a cadena mostramos su título
        return title
    }

    companion object {
        const val FORMAT_DVD = 0 // Formatos
        const val FORMAT_BLURAY = 1
        const val FORMAT_DIGITAL = 2
        const val GENRE_ACTION = 0 // Géneros
        const val GENRE_COMEDY = 1
        const val GENRE_DRAMA = 2
        const val GENRE_SCIFI = 3
        const val GENRE_HORROR = 4
    }
}

object FilmDataSource {
    val films: MutableList<Film> = mutableListOf()

    init {
        // Primera película: Harry Potter y la piedra filosofal
        val f1 = Film()
        f1.id = films.size
        f1.title = "Harry Potter y la piedra filosofal"
        f1.director = "Chris Columbus"
        f1.imageResId = R.drawable.harry_potter_y_la_piedra_filosofal
        f1.comments = "Una aventura mágica en Hogwarts."
        f1.format = Film.FORMAT_DVD
        f1.genre = Film.GENRE_ACTION // Cambia según corresponda
        f1.imdbUrl = "http://www.imdb.com/title/tt0241527"
        f1.year = 2001
        films.add(f1)

        // Segunda película: Regreso al futuro
        val f2 = Film()
        f2.id = films.size
        f2.title = "Regreso al futuro"
        f2.director = "Robert Zemeckis"
        f2.imageResId = R.drawable.regreso_al_futuro
        f2.comments = ""
        f2.format = Film.FORMAT_DIGITAL
        f2.genre = Film.GENRE_SCIFI
        f2.imdbUrl = "http://www.imdb.com/title/tt0088763"
        f2.year = 1985
        films.add(f2)

        // Tercera película: El rey león
        val f3 = Film()
        f3.id = films.size
        f3.title = "El rey león"
        f3.director = "Roger Allers, Rob Minkoff"
        f3.imageResId = R.drawable.el_rey_leon
        f3.comments = "Una historia de crecimiento y responsabilidad."
        f3.format = Film.FORMAT_BLURAY
        f3.genre = Film.GENRE_ACTION // Cambia según corresponda
        f3.imdbUrl = "http://www.imdb.com/title/tt0110357"
        f3.year = 1994
        films.add(f3)

        // Añade más películas si deseas!
    }
}