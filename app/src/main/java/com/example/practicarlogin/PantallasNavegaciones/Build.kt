package com.example.practicarlogin.PantallasNavegaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicarlogin.R
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.piezas.CPU

private var cpuElegido: Boolean = false
@Composable
fun buildPC(viewModell: ComponentViewModel, navigate: (Int) -> Unit) {


    val component by viewModell.component.observeAsState()

    cpuElegido = component?.nombre != null

    val unlockMother by viewModell.procesador.observeAsState(true)


    val idioma: languages = LenguajeSeleccionado().idioma()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 50.dp)
            ) {

                Text(
                    idioma.tituloBuild,
                    fontFamily = Fuentes.mulishExtraBold,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
                Spacer(modifier = Modifier.padding(10.dp))

                //Procesador
                AnimatedVisibility(!cpuElegido) {

                    cargarInformacion(
                        idioma.procesador,
                        lock = false,
                        painterResource(R.drawable.proccesor)
                    ) { navigate(0) }
                    viewModell.lockBoard()
                }
                if(cpuElegido){
                    viewModell.unlockBoard()
                    cargarProcesador(component,{viewModell.borrarCPU()}, {navigate(0)})
                }
                Spacer(modifier = Modifier.padding(10.dp))


                //Placa base
                cargarInformacion(
                    idioma.placabase,
                    !unlockMother,
                    painterResource(R.drawable.motherboard)
                ) { navigate(1) }

                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    "RAM",
                    lock = true,
                    painterResource(R.drawable.ram)
                ) { navigate(2) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.Almacenamiento,
                    lock = true,
                    painterResource(R.drawable.ssd_)
                ) { navigate(3) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.tarjeta,
                    lock = true,
                    painterResource(R.drawable.graphic)
                ) { navigate(4) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.caja,
                    lock = true,
                    painterResource(R.drawable.resource_case)
                ) { navigate(5) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.fuente,
                    lock = true,
                    painterResource(R.drawable.power)
                ) { navigate(6) }
            }
        }
    }
}

@Composable
fun cargarProcesador(cpu: CPU?, function: () -> Unit, remplazar: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .wrapContentHeight(),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Row {
                AsyncImage(
                    model = cpu?.imagen,
                    contentDescription = "",
                    modifier = Modifier
                        .size(95.dp)
                        .clip(shape = RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 10.dp, end = 8.dp)) {
                    Text(
                        "${cpu?.nombre}",
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontSize = 14.5.sp,
                        fontFamily = Fuentes.mulishBold
                    )
                    Text(
                        "${cpu?.precio}€",
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontSize = 14.5.sp,
                        fontFamily = Fuentes.mulishSemiBold
                    )
                    Row {
                        Text(
                            "${languageSelect.nucleos}: ${cpu?.nucleos}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "${languageSelect.hilos}: ${cpu?.nucleos}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1.1f)
                        )

                    }
                    Row {
                        Text(
                            "Reloj: ${cpu?.frecuencia}GHz",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "TDP: ${cpu?.tdp}w",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1.1f)
                        )


                    }


                }

            }
            Spacer(modifier = Modifier.padding(3.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(onClick = {remplazar()},modifier = Modifier.weight(5f)) {
                    Text(
                        "Remplazar",
                        fontFamily = Fuentes.mulishBold,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))
                IconButton(onClick = {function()},modifier = Modifier.border(width = 0.8.dp, color = MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(15.dp)).weight(1f).height(37.dp).width(37.dp), colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.onSecondary) ,content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Quitar", tint = colorResource(R.color.blue)
                    )
                })
            }

        }


    }
}

@Composable
fun cargarInformacion(titulo: String, lock: Boolean, image: Painter, navigate: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .wrapContentHeight()
            .clickable {
                if (!lock) {
                    navigate()
                }
            },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .weight(1f)
                    .padding(15.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (!lock) {
                            R.drawable.plus_sign
                        } else {
                            R.drawable.lock
                        }
                    ),
                    contentDescription = "",
                    tint = colorResource(R.color.blue)
                )
            }
            Text(
                titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontFamily = Fuentes.mulishSemiBold
            )
            Icon(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                tint = colorResource(R.color.blue)
            )
        }
    }
}