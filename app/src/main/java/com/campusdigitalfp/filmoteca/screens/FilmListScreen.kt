package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.campusdigitalfp.filmoteca.common.FilmDataSource

@Preview(showBackground = true)
@Composable
fun PreviewFilmListScreen() {
    FilmListScreen(rememberNavController() )
}

@Composable
fun FilmListScreen(navController: NavHostController)   {
    Scaffold(topBar = { BarraSuperiorComun(navController) },
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn {
                    items(FilmDataSource.films) { film ->
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.cartel),
                                contentDescription = stringResource(R.string.perfil_desarrollador),
                                modifier = Modifier
                                    .size(90.dp)
                            )
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
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("about") }) {
                    Text(stringResource(R.string.acerca_de))
                }
            }
        }
    )
}
