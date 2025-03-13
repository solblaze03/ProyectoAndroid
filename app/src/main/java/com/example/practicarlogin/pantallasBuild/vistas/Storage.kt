package com.example.practicarlogin.pantallasBuild.vistas

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
import com.example.practicarlogin.piezas.storage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Storage(
    component: storage,
    viewModel: ComponentViewModel,
    volver: () -> Unit,
) {
    val board by viewModel.Board.observeAsState()
    Scaffold(
        content = { contentStorage(component, board) },
        floatingActionButton = { fabStorage(component, viewModel, { volver() }) },
        floatingActionButtonPosition = FabPosition.End
    )
}


@Composable
fun contentStorage(component: storage, board: Board?) {


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
                            var painter: Painter = if (component.marca.equals("Samsung")) {
                                painterResource(R.drawable.samsung)
                            } else if (component.marca.equals("G.Skill")) {
                                painterResource(R.drawable.gskill)
                            } else if (component.marca.equals("Kingstone") || component.marca.equals(
                                    "Kingston"
                                )
                            ) {
                                painterResource(R.drawable.kingstone)
                            } else if (component.marca.equals("Crucial")) {
                                painterResource(R.drawable.crucial)
                            } else if (component.marca.equals("Patriot")) {
                                painterResource(R.drawable.patriot)
                            } else if (component.marca.equals("Team T-Force")) {
                                painterResource(R.drawable.tforce)
                            } else if (component.marca.equals("Intel")) {
                                painterResource(R.drawable.intellogo)
                            } else {
                                painterResource(R.drawable.logo)
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
                Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                    Text(
                        "Especificaciones",
                        fontSize = 18.sp,
                        fontFamily = Fuentes.mulishBold,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.padding(6.dp))


                    Text(
                        "Cantidad: ${component.tamaño}",
                        fontSize = 15.sp,
                        fontFamily = Fuentes.mulishRegular,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Text(
                        "Velocidad escritura: ${component.VE}",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Text(
                        "Velocidad lectura: ${component.Vl}",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Text(
                        "Tipo: ${component.tipo}",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Text(
                        "Marca: ${component.marca}",
                        color = MaterialTheme.colorScheme.inverseSurface
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                }


            }
            Spacer(modifier = Modifier.padding(8.dp))


        }
    }
}

@Composable
fun fabStorage(
    storage: storage,
    viewModel: ComponentViewModel,
    function: () -> Unit,
) {
    val color = colorResource(
        if (storage.marca.equals("Gigabyte")) {
            R.color.azulIntel
        } else if (storage.marca.equals("ASRock")) {
            R.color.verde
        } else {
            R.color.rojo
        }
    )
    FloatingActionButton(
        // viewModel.lockBoardOptions();viewModel.unlockStorage(); viewModel.cambiarComponente(2); viewModel.guardarRAM(ram) ; function()
        onClick = {
            viewModel.unlockGraphic(); viewModel.cambiarComponente(3);viewModel.guardarAlmacenamiento(
            storage
        );function()
        },
        containerColor = color
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
    }
}

