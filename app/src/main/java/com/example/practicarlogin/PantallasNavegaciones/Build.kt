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
import cargarBoard
import cargarInformacion
import cargarProcesador
import coil3.compose.AsyncImage
import com.example.practicarlogin.R
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.CPU

private var cpuElegido: Boolean = false
private var boardelegido: Boolean = false
private var RAMelegido: Boolean = false

@Composable
fun buildPC(viewModell: ComponentViewModel, navigate: (Int) -> Unit) {


    val component by viewModell.component.observeAsState()
    val boardElegida by viewModell.Board.observeAsState()
    val ram by viewModell.Board.observeAsState()


    RAMelegido = ram?.nombre != null
    cpuElegido = component?.nombre != null
    boardelegido = boardElegida?.nombre != null

    val unlockMother by viewModell.procesador.observeAsState(true)
    val unlockRAM by viewModell.RAM.observeAsState(true)
    val lockProcesador by viewModell.processorOptions.observeAsState(false)

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
                        painterResource(R.drawable.proccesor),
                        { navigate(0) }
                    ) { viewModell.cambiarComponente(0) }
                    viewModell.lockBoard()
                }
                if (cpuElegido) {
                    viewModell.unlockBoard()
                    cargarProcesador(
                        component,
                        { viewModell.borrarCPU() },
                        { navigate(0) },
                        { viewModell.cambiarComponente(0) },lockProcesador)
                }
                Spacer(modifier = Modifier.padding(10.dp))

                //Placa base

                AnimatedVisibility(!boardelegido) {
                    cargarInformacion(
                        idioma.placabase,
                        !unlockMother,
                        painterResource(R.drawable.motherboard),
                        { navigate(1) },
                        { viewModell.cambiarComponente(1) })
                }
                if (boardelegido) {
                    cargarBoard(
                        boardElegida,
                        { viewModell.borrarBoard() },
                        { navigate(1) },
                        { viewModell.cambiarComponente(1) },
                        {viewModell.unlockProccesorOptions()},{viewModell.lockRAM()})
                }
                Spacer(modifier = Modifier.padding(10.dp))
                //RAM
                cargarInformacion(
                    "RAM",
                    !unlockRAM,
                    painterResource(R.drawable.ram),
                    { navigate(2) }
                ) { viewModell.cambiarComponente(2) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.Almacenamiento,
                    lock = true,
                    painterResource(R.drawable.ssd_),
                    { navigate(3) }
                ) { viewModell.cambiarComponente(3) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.tarjeta,
                    lock = true,
                    painterResource(R.drawable.graphic),
                    { navigate(4) }
                ) { viewModell.cambiarComponente(4) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.caja,
                    lock = true,
                    painterResource(R.drawable.resource_case),
                    { navigate(5) }
                ) { viewModell.cambiarComponente(5) }
                Spacer(modifier = Modifier.padding(10.dp))
                cargarInformacion(
                    idioma.fuente,
                    lock = true,
                    painterResource(R.drawable.power),
                    { navigate(6) }
                ) { viewModell.cambiarComponente(6) }
            }
        }
    }
}



