package com.example.practicarlogin.pantallasBuild.vistas

import com.example.practicarlogin.piezas.Graphic

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardColors
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.RAM
import com.example.practicarlogin.piezas.fuente

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Psu(
    component: fuente,
    viewModel: ComponentViewModel,
    volver: () -> Unit) {
    val board by viewModel.Board.observeAsState()
    Scaffold(
        content = { contentPSU(component, board) },
        floatingActionButton = { fabPSU(component, viewModel, { volver() }) },
        floatingActionButtonPosition = FabPosition.End
    )
}


@Composable
fun contentPSU(component: fuente, board: Board?) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(top = 40.dp)
            .background(color = MaterialTheme.colorScheme.onSecondary)


    ) {
        item {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(component.imagen)
                        .crossfade(enable = true).placeholder(R.drawable.logo)
                        .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = "Imagen",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(370.dp)
                )
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp),
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent
                    )
                ) {

                    Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(3f)) {

                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(
                                    component.nombre,
                                    fontFamily = Fuentes.mulishExtraBold,
                                    fontSize = 21.sp,
                                    color = MaterialTheme.colorScheme.inverseSurface
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(
                                    "${component.precio}€", fontSize = 21.sp,
                                    color = colorResource(
                                        if (component.marca.equals("Gigabyte")) {
                                            R.color.azulIntel
                                        } else if (component.marca.equals("ASRock")) {
                                            R.color.verde
                                        } else {
                                            R.color.rojo
                                        }
                                    )
                                )
                            }
                            var painter: Painter = if (component.marca.equals("Corsair")) {
                                painterResource(R.drawable.corsair)
                            } else if (component.marca.equals("G.Skill")) {
                                painterResource(R.drawable.gskill)
                            } else if (component.marca.equals("Kingstone")) {
                                painterResource(R.drawable.kingstone)
                            } else if (component.marca.equals("Crucial")) {
                                painterResource(R.drawable.crucial)
                            } else if (component.marca.equals("Patriot")) {
                                painterResource(R.drawable.patriot)
                            } else if (component.marca.equals("Team T-Force")) {
                                painterResource(R.drawable.tforce)
                            } else {
                                painterResource(R.drawable.xpg)
                            }

                            Image(
                                painter = painter,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(50.dp)
                                    .weight(1.2f)
                                    .padding(top = 2.dp)
                            )
                        }




                        Spacer(modifier = Modifier.padding(10.dp))


                    }


                }
                Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 42.dp)) {
                    Text(
                        "Especificaciones",
                        fontSize = 18.sp,
                        fontFamily = Fuentes.mulishBold,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.padding(6.dp))

                        Text(
                            "Certificación: ${component.certificacion}",
                            fontSize = 15.sp,
                            fontFamily = Fuentes.mulishRegular,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            "Modularidad: ${component.modularidad}",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    Spacer(modifier = Modifier.padding(2.dp))



                        Text(
                            "Potencia: ${component.potencia}",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            "Marca: ${component.marca}",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )


                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(
                        "Conectores: ${component.conectores}W",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )





                    Spacer(modifier = Modifier.padding(2.dp))



                }


            }
            Spacer(modifier = Modifier.padding(8.dp))


        }
    }
}

@Composable
fun fabPSU(
    psu: fuente,
    viewModel: ComponentViewModel,
    function: () -> Unit,
) {
    val color = colorResource(
        if (psu.marca.equals("Gigabyte")) {
            R.color.azulIntel
        } else if (psu.marca.equals("ASRock")) {
            R.color.verde
        } else {
            R.color.rojo
        }
    )
    FloatingActionButton(

        onClick = { function(); viewModel.guardarPsu(psu) },
        containerColor = color
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
    }
}

