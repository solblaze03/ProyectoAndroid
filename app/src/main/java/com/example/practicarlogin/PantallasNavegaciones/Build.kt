package com.example.practicarlogin.PantallasNavegaciones

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.practicarlogin.R
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.language.languages


@Composable
fun buildPC(function: () -> Unit) {

    val idioma: languages = LenguajeSeleccionado().idioma()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Text(idioma.tituloBuild, fontFamily = Fuentes.mulishExtraBold)
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.procesador,
                    lock = false,
                    painterResource(R.drawable.proccesor)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.placabase,
                    lock = true,
                    painterResource(R.drawable.motherboard)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion("RAM", lock = true, painterResource(R.drawable.ram))
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.Almacenamiento,
                    lock = true,
                    painterResource(R.drawable.ssd_)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(idioma.tarjeta, lock = true, painterResource(R.drawable.graphic))
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.caja,
                    lock = true,
                    painterResource(R.drawable.resource_case)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(idioma.fuente, lock = true, painterResource(R.drawable.power))
            }
        }
    }
}

@Composable
fun cargarInformacion(titulo: String, lock: Boolean, image: Painter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
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