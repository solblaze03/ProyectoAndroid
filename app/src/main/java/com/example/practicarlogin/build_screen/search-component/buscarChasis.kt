package com.example.practicarlogin.build_screen.`search-component`

import android.annotation.SuppressLint
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
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.models.Caja
import com.example.practicarlogin.models.ListaPiezas
import com.google.gson.Gson

private val language = LenguajeSeleccionado().idioma()

@SuppressLint("RememberReturnType")
@Composable
fun buscarChasis(
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
        val graphic by viewModel.graphic.observeAsState()
        val board by viewModel.Boards.observeAsState()
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


        var lista by remember { mutableStateOf(emptyList<Caja>()) }

        if (board?.factorForma.equals("ATX")) {
            lista = ListaPiezas.cajaList.filter { e ->
                (e.nombre.contains(
                    busqueda,
                    ignoreCase = true
                ) || e.marca.contains(
                    busqueda,
                    ignoreCase = true
                )) && e.factorForma.equals("ATX") && (graphic?.altura!! < e.alturaGPU) && (graphic?.longitud!! < e.longitudMaximaGPU)
            }
            Log.i("case", "1")
        } else if (board?.factorForma.equals("Micro-ATX")) {
            lista = ListaPiezas.cajaList.filter { e ->
                (e.nombre.contains(
                    busqueda,
                    ignoreCase = true
                ) || e.marca.contains(
                    busqueda,
                    ignoreCase = true
                ))
                        && (e.factorForma.equals("ATX") || e.factorForma.equals("Micro-ATX")) && (graphic?.altura!! < e.alturaGPU) && (graphic?.longitud!! < e.longitudMaximaGPU)
            }
            Log.i("case", "2")
        } else {
            lista = ListaPiezas.cajaList.filter { e ->
                (e.nombre.contains(
                    busqueda,
                    ignoreCase = true
                ) || e.marca.contains(
                    busqueda,
                    ignoreCase = true
                )) && (e.factorForma.equals("ATX") || e.factorForma.equals("Mini-ITX") || e.factorForma.equals(
                    "Micro-ATX"
                )) && (graphic?.altura!! < (e.alturaGPU + 5)) && (graphic?.longitud!! < (e.longitudMaximaGPU + 5))
            }
            Log.i("case", "3")
        }
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 15.dp, end = 15.dp)
        ) {
            items(lista) { e ->

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


                                Text(
                                    "Forma: ${e.factorForma} ",
                                    fontSize = 13.sp,
                                    fontFamily = Fuentes.mulishRegular
                                )
                                Text(
                                    "Peso ${e.peso} ",
                                    fontSize = 13.sp,
                                    fontFamily = Fuentes.mulishRegular
                                )


                                Row(modifier = Modifier) {
                                    Text(
                                        "Precio:",
                                        color = MaterialTheme.colorScheme.inverseSurface,
                                        fontSize = 16.sp,
                                        fontFamily = Fuentes.mulishBold,
                                    )
                                    Text(
                                        "${e?.precio}€",
                                        color = MaterialTheme.colorScheme.inverseSurface,
                                        fontSize = 16.sp,
                                        fontFamily = Fuentes.mulishBold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 28.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.padding(5.dp))


                            }


                        }

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
                                    // viewModel.unlockStorage(); lockBoard(); viewModel.guardarRAM(e); Volver(); viewModel.cambiarComponente( 1 )
                                    viewModel.lockGraphicOptions();viewModel.unlockPsu();viewModel.guardarCaja(e); Volver();viewModel.cambiarComponente(
                                    4
                                );
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
                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }
}