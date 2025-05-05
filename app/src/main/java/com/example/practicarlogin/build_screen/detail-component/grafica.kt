package com.example.practicarlogin.build_screen.`detail-component`

import com.example.practicarlogin.models.Graphic

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
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.models.Board
import kotlin.collections.hashMapOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun viewGrafica(
    component: Graphic,
    viewModel: ComponentViewModel,
    volver: () -> Unit,

    ) {
    val board by viewModel.Boards.observeAsState()
    Scaffold(
        content = { contentGraphic(component, board) },
        floatingActionButton = { fabGraphic(component, viewModel, { volver() }) },
        floatingActionButtonPosition = FabPosition.End
    )
}


@Composable
fun contentGraphic(component: Graphic, board: Board?) {


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



                            val painter : Painter = getColorByBrand(component.marca)


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
                    Row {

                        Text(
                            "VRAM: ${component.vram}",
                            modifier = Modifier.weight(1f),
                            fontSize = 15.sp,
                            fontFamily = Fuentes.mulishRegular,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                        val rtx = if (component.rtx) {
                            "Si"
                        } else {
                            "No"
                        }
                        Text(
                            "RTX: ${rtx}",
                            modifier = Modifier.weight(1f),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )

                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Row {
                        Text(
                            "Tipo: ${component.tipoMemoria}",
                            modifier = Modifier.weight(1.1f),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                        Text(
                            "Marca: ${component.marca}",
                            modifier = Modifier.weight(1.1f),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )

                    }
                    Spacer(modifier = Modifier.padding(2.dp))

                        Text(
                            "TDP: ${component.consumo}W",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )





                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        "Ensamblador: ${component.ensamblador}",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        "Conectores: ${component.conectoresPantalla}",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )


                }
                Spacer(modifier = Modifier.padding(5.dp))

            }
            Spacer(modifier = Modifier.padding(8.dp))


        }
    }
}

@Composable
fun fabGraphic(
    grafica: Graphic,
    viewModel: ComponentViewModel,
    function: () -> Unit,
) {
    val color = colorResource(
        if (grafica.ensamblador.equals("Gigabyte")) {
            R.color.azulIntel
        } else if (grafica.ensamblador.equals("ASRock")) {
            R.color.verde
        } else {
            R.color.rojo
        }
    )
    FloatingActionButton(
        //viewModel.lockBoardOptions();viewModel.unlockStorage(); viewModel.cambiarComponente(2); viewModel.guardarRAM(ram) ; function()
        onClick = { viewModel.unlockCase();function();viewModel.guardarGrafica(grafica) },
        containerColor = color
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
    }
}

@Composable
fun getColorByBrand(brand: String) : Painter {

    val list : HashMap<String, Painter> = hashMapOf(
        "Corsair" to painterResource(R.drawable.corsair),
        "Gskill" to painterResource(R.drawable.gskill),
        "Kingstone" to painterResource(R.drawable.kingstone),
        "Crucial" to painterResource(R.drawable.crucial),
        "Patriot" to painterResource(R.drawable.patriot),
        "Tforce" to painterResource(R.drawable.tforce),
        "XPG" to painterResource(R.drawable.xpg),
        "NVIDIA" to painterResource(R.drawable.logo),
        "AMD" to painterResource(R.drawable.graphic)
        )

    return if(list.containsKey(brand)) list.getValue(brand) else{ painterResource(R.drawable.graphic)}
}
