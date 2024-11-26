package com.example.practicarlogin.pantallasBuild

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.example.practicarlogin.R
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.languageSelect

import com.example.practicarlogin.piezas.ListaPiezas
import com.google.gson.Gson

private val language = LenguajeSeleccionado().idioma()

@Composable
fun productos(
    component: Int,
    function: (String) -> Unit,
    Volver: () -> Unit,
    viewModel: ComponentViewModel
) {


    val componentSeleccionado by viewModel.componentSeleccionado.observeAsState(initial = 0)
    Log.i("componente", "$componentSeleccionado")

    when (componentSeleccionado){
        0 -> ListaProcesador(function, viewModel, Volver, component)
        1-> MotherBoardpc(function, viewModel, Volver) { viewModel.lockProccesorOptions() }
    }


}


@Composable
fun MotherBoardpc(
    function: (String) -> Unit,
    viewModel: ComponentViewModel,
    Volver: () -> Unit,
    lockProcessor: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        var busqueda by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.padding(top = 50.dp))
        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 20.dp, end = 10.dp),
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = {

                Text(
                    text = languageSelect.buscar,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray
                )
            }

        )

        val filtrarItems = remember(busqueda) {

            ListaPiezas.boards.filter { e ->
                e.nombre.contains(
                    busqueda,
                    ignoreCase = true
                ) || e.marca.contains(busqueda, ignoreCase = true)
            }
        }

        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 15.dp, end = 15.dp)
        ) {
            items(filtrarItems) { e ->

                val component = Gson().toJson(e)



                HorizontalDivider(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.padding(5.dp))
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(shape = RoundedCornerShape(10.dp)) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(e.urlImagen)
                                        .crossfade(enable = true)
                                        .placeholder(R.drawable.logo)
                                        .diskCachePolicy(CachePolicy.ENABLED)
                                        .memoryCachePolicy(CachePolicy.ENABLED)
                                        .build(),
                                    contentDescription = e.nombre,
                                    modifier = Modifier.size(105.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            Column {
                                Text(
                                    e.nombre,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Left,
                                    fontFamily = Fuentes.mulishBold
                                )
                                Spacer(modifier = Modifier.padding(3.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 10.dp)
                                ) {
                                    Text(
                                        "${language.marca}: ${e.marca} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )
                                    Text(
                                        "${language.zocalo}: ${e.socket} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 10.dp)
                                ) {
                                    Text(
                                        "Chipset : ${e.chipset} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )
                                    Text(
                                        "Slots: ${e.slotsRam} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )

                                }
                                Text(
                                    "${e.precio}",
                                    fontSize = 15.sp,
                                    fontFamily = Fuentes.mulishSemiBold,
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .padding(start = 1.dp)
                                )
                                Spacer(modifier = Modifier.padding(5.dp))

                                Row {
                                    OutlinedButton(
                                        onClick = { function(component) },
                                        modifier = Modifier
                                            .weight(2f)
                                            .height(38.dp),
                                        border = BorderStroke(
                                            1.5.dp,
                                            color = colorResource(R.color.blue)
                                        ),

                                        content = {
                                            Text(
                                                languageSelect.vermas,
                                                fontFamily = Fuentes.mulishBold,
                                                fontSize = 13.sp,
                                                color = colorResource(R.color.teal_700)
                                            )
                                        })
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Button(
                                        onClick = { viewModel.unlockRAM();lockProcessor();viewModel.guardarBoard(e);Volver() },
                                        modifier = Modifier
                                            .weight(1.5f)
                                            .height(38.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = colorResource(
                                                R.color.blue
                                            )
                                        ),
                                        content = {
                                            Text(
                                                languageSelect.agregar,
                                                color = Color.White
                                            )
                                        }
                                    )
                                }
                            }


                        }


                    }

                }
                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }
}

@Composable
fun ListaProcesador(
    function: (String) -> Unit,
    viewModel: ComponentViewModel,
    Volver: () -> Unit,
    component: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        var busqueda by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.padding(top = 50.dp))
        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 20.dp, end = 10.dp),
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = {

                Text(
                    text = languageSelect.buscar,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray
                )
            }

        )

        val filtrarItems = remember(busqueda) {

            ListaPiezas.procesadores.filter { e ->
                e.nombre.contains(
                    busqueda,
                    ignoreCase = true
                ) || e.marca.contains(busqueda, ignoreCase = true)
            }
        }

        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 15.dp, end = 15.dp)
        ) {
            items(filtrarItems) { e ->

                val component = Gson().toJson(e)



                HorizontalDivider(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.padding(5.dp))
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(shape = RoundedCornerShape(10.dp)) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(e.imagen)
                                        .crossfade(enable = true)
                                        .placeholder(R.drawable.logo)
                                        .diskCachePolicy(CachePolicy.ENABLED)
                                        .memoryCachePolicy(CachePolicy.ENABLED)
                                        .build(),
                                    contentDescription = e.nombre,
                                    modifier = Modifier.size(105.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            Column {
                                Text(
                                    e.nombre,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Left,
                                    fontFamily = Fuentes.mulishBold
                                )
                                Spacer(modifier = Modifier.padding(3.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 10.dp)
                                ) {
                                    Text(
                                        "${language.marca}: ${e.marca} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )
                                    Text(
                                        "${language.zocalo}: ${e.socket} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 10.dp)
                                ) {
                                    Text(
                                        "${language.nucleos}: ${e.nucleos} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )
                                    Text(
                                        "${language.hilos}: ${e.hilos} ",
                                        fontSize = 13.sp,
                                        modifier = Modifier.weight(1f),
                                        fontFamily = Fuentes.mulishRegular
                                    )

                                }
                                Text(
                                    "${e.precio}€",
                                    fontSize = 15.sp,
                                    fontFamily = Fuentes.mulishSemiBold,
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .padding(start = 1.dp)
                                )
                                Spacer(modifier = Modifier.padding(5.dp))

                                Row {
                                    OutlinedButton(
                                        onClick = { function(component) },
                                        modifier = Modifier
                                            .weight(2f)
                                            .height(38.dp),
                                        border = BorderStroke(
                                            1.5.dp,
                                            color = colorResource(R.color.blue)
                                        ),

                                        content = {
                                            Text(
                                                languageSelect.vermas,
                                                fontFamily = Fuentes.mulishBold,
                                                fontSize = 13.sp,
                                                color = colorResource(R.color.teal_700)
                                            )
                                        })
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Button(
                                        onClick = {
                                            viewModel.guardarCPU(e);Volver(); viewModel.cambiarComponente(
                                            1
                                        )
                                        },
                                        modifier = Modifier
                                            .weight(1.5f)
                                            .height(38.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = colorResource(
                                                R.color.blue
                                            )
                                        ),
                                        content = {
                                            Text(
                                                languageSelect.agregar,
                                                color = Color.White
                                            )
                                        }
                                    )
                                }
                            }


                        }


                    }

                }
                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }
}