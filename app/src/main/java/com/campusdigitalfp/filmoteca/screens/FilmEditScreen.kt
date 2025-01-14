package com.campusdigitalfp.filmoteca.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@Preview(showBackground = true)
@Composable
fun PreviewFilmEditScreen() {
    FilmEditScreen(rememberNavController() )
}

@Composable
fun FilmEditScreen(navController: NavHostController)   {
    var text by remember { mutableStateOf("") }
    var expandedGenero by remember { mutableStateOf(false) }
    var expandedFormato by remember { mutableStateOf(false) }
    val generoList = listOf("Acción","Drama","Comedia","Terror","Sci-Fi")
    val formatoList = listOf("DVD","Blu-ray","Online")
    var genero by remember { mutableStateOf(generoList[0]) }
    var formato by remember { mutableStateOf(formatoList[0]) }
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
                        painter = painterResource(id = R.drawable.cartel),
                        contentDescription = stringResource(R.string.perfil_desarrollador),
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
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text(stringResource(R.string.titulo).plus(":")) },
                    placeholder = { Text(stringResource(R.string.titulo)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text(stringResource(R.string.director).plus(":")) },
                    placeholder = { Text(stringResource(R.string.director)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
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

                    Text(genero, modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedGenero = true }
                    )

                    DropdownMenu(
                        expanded = expandedGenero,
                        onDismissRequest = { expandedGenero = false }
                    ) {
                        generoList.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
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

                    Text(formato, modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedFormato = true }
                    )

                    DropdownMenu(
                        expanded = expandedFormato,
                        onDismissRequest = { expandedFormato = false }
                    ) {
                        formatoList.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    formato = option
                                    expandedFormato = false
                                }
                            )
                        }
                    }
                }
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text(stringResource(R.string.imdb_url)) },
                    placeholder = { Text(stringResource(R.string.imdb_url)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
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
                        navController.popBackStack()
                    }) {
                        Text(stringResource(R.string.guardar))
                    }
                    Button(onClick = {
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
