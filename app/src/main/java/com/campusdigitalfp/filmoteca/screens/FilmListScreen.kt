package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun
import com.campusdigitalfp.filmoteca.common.FilmDataSource

@Composable
fun FilmListScreen(navController: NavHostController)   {
    Scaffold(topBar = { BarraSuperiorComun(navController) },
        content = { paddingValues ->
            Box(
                Modifier.padding(paddingValues).fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement  = Arrangement.Center) {
                    LazyColumn(modifier = Modifier.padding(paddingValues)) {
                        items(FilmDataSource.films) { film ->
                            film.title?.let {
                                Button(onClick = { navController.navigate("view/".plus(it)) }) {
                                     Text(it)
                                }
                            }
                        }
                    }

                    Button(onClick = { navController.navigate("about") }) {
                        Text(stringResource(R.string.acerca_de))
                    }
                }
            }
        }
    )
}
