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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.pantallasBuild.idiomaSeleccionado
import com.example.practicarlogin.piezas.CPU

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun procesador(component: CPU, viewModel: ComponentViewModel, function: () ->  Unit) {
    Scaffold (
        content = { cargarUI(component) },
        floatingActionButton = { fab(component,viewModel){ function() } },
        floatingActionButtonPosition = FabPosition.End
    )
}


@Composable
fun cargarUI(component: CPU) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(top = 40.dp).background(color = MaterialTheme.colorScheme.onSecondary)


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
                            Column (modifier = Modifier.weight(3f)) {
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(
                                    component.nombre,
                                    fontFamily = Fuentes.mulishExtraBold,
                                    fontSize = 21.sp,
                                    color = MaterialTheme.colorScheme.inverseSurface

                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text("${component.precio}€", fontSize = 21.sp,
                                    color = colorResource(if(component.marca.equals("AMD")){
                                        R.color.naranja}else{
                                        R.color.azulIntel})
                                )
                            }
                            Image(
                                painter = if (component.marca.contains("AMD")) {
                                    painterResource(R.drawable.ryzen)
                                } else {
                                    painterResource(R.drawable.intellogo)
                                },
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
                Column (modifier = Modifier.padding(start = 15.dp, end = 15.dp)){
                    Text("Especificaciones",
                        fontSize = 18.sp,
                        fontFamily = Fuentes.mulishBold,
                        color = MaterialTheme.colorScheme.inverseSurface)
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.padding(6.dp))
                    Row {

                        Text(
                            "${idiomaSeleccionado.nucleos}: ${component.nucleos}",
                            modifier = Modifier.weight(1f),
                            fontSize = 15.sp,
                            fontFamily = Fuentes.mulishRegular,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                        Text(
                            "${idiomaSeleccionado.hilos}: ${component.hilos}",
                            modifier = Modifier.weight(1f),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                        Text(
                            "${idiomaSeleccionado.zocalo}: ${component.socket}",
                            modifier = Modifier.weight(1.1f),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row {

                        Text(
                            "${languageSelect.reloj}: ${component.frecuencia}GHz",
                            modifier = Modifier.weight(1f),
                            fontFamily = Fuentes.mulishRegular,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                        Text(
                            "TDP: ${component.tdp}w",
                            modifier = Modifier.weight(1f),
                            fontSize = 15.sp,
                            fontFamily = Fuentes.mulishRegular,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                        Text(
                            "${languageSelect.litografia}: ${component.tecnologia}",
                            modifier = Modifier.weight(1.1f),
                            fontSize = 15.sp,
                            fontFamily = Fuentes.mulishRegular,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    "GPU:  ${component.grafica}",
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                    fontFamily = Fuentes.mulishRegular,
                    color = MaterialTheme.colorScheme.inverseSurface
                )

            }
            Spacer(modifier = Modifier.padding(8.dp))



        }
    }
}

@Composable
fun fab(cpu: CPU, viewModel: ComponentViewModel, regresar: () -> Unit){

    val color = if(cpu.marca.equals("AMD")){
        R.color.naranja}else{
        R.color.azulIntel}
    FloatingActionButton(onClick = {regresar();viewModel.guardarCPU(cpu);viewModel.cambiarComponente(1)}, containerColor =  colorResource(color)) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
    }
}