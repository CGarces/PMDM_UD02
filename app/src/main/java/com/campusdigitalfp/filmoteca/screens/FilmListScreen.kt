package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@Composable
fun FilmListScreen(navController: NavHostController)   {
    Scaffold(topBar = { BarraSuperiorComun(navController) },
        content = { paddingValues ->
            Box(
                Modifier.padding(paddingValues).fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement  = Arrangement.Center) {
                    Button(onClick = { navController.navigate("view/Harry Potter y la piedra Filosofal") }) {
                        Text(stringResource(R.string.ver, "A"))
                    }
                    Button(onClick = { navController.navigate("view/B") }) {
                        Text(stringResource(R.string.ver, "B"))
                    }
                    Button(onClick = { navController.navigate("about") }) {
                        Text(stringResource(R.string.acerca_de))
                    }
                }
            }
        }
    )
}
