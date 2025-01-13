package com.campusdigitalfp.filmoteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.screens.AboutScreen
import com.campusdigitalfp.filmoteca.screens.FilmDataScreen
import com.campusdigitalfp.filmoteca.screens.FilmEditScreen
import com.campusdigitalfp.filmoteca.screens.FilmListScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") { FilmListScreen(navController) }
        composable("about") { AboutScreen(navController) }
        composable("view/{pelicula}") { backStackEntry ->
            val pelicula = backStackEntry.arguments?.getString("pelicula")?.toString()
            pelicula?.let {
               FilmDataScreen(navController, pelicula)
            }
        }
        composable("edit") { FilmEditScreen(navController) }



    }
}