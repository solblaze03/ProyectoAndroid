package com.example.practicarlogin.PantallasNavegaciones

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cargarBoard
import cargarCase
import cargarGrafica
import cargarInformacion
import cargarProcesador
import com.example.practicarlogin.PantallasNavegaciones.CARDS.cargarPsu
import com.example.practicarlogin.PantallasNavegaciones.CARDS.cargarRAM
import com.example.practicarlogin.PantallasNavegaciones.CARDS.cargarStorage
import com.example.practicarlogin.R
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.ui.theme.PracticarLoginTheme

private var cpuElegido: Boolean = false
private var boardelegido: Boolean = false
private var RAMelegido: Boolean = false
private var Storageelegido: Boolean = false
private var GraphicElegida: Boolean = false
private var CaseElegida: Boolean = false
private var psuElegida: Boolean = false
private val idioma: languages = LenguajeSeleccionado().idioma()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun buildPC(viewModell: ComponentViewModel, navigate: (Int) -> Unit) {
    PracticarLoginTheme {


        Scaffold(
            topBar = { topBar() },
            content = { content(viewModell, navigate) }
        )


    }
}

@Composable
fun topBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(97.dp)
            .background(color = MaterialTheme.colorScheme.onSecondary)
    ) {


        Text(
            idioma.tituloBuild,
            fontFamily = Fuentes.mulishBold,
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            fontSize = 17.sp
        )
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.align(Alignment.BottomCenter))


    }

}

@Composable
fun content(viewModell: ComponentViewModel, navigate: (Int) -> Unit) {

    val component by viewModell.component.observeAsState()
    val boardElegida by viewModell.Board.observeAsState()
    val ram by viewModell.ram.observeAsState()
    val almacenamiento by viewModell.storage.observeAsState()
    val grafica by viewModell.graphic.observeAsState()
    val case by viewModell.chasis.observeAsState()
    val psu by viewModell.psuComponent.observeAsState()

    //Si el usuario eligio un componente
    RAMelegido = ram?.nombre != null
    cpuElegido = component?.nombre != null
    boardelegido = boardElegida?.nombre != null
    Storageelegido = almacenamiento?.nombre != null
    GraphicElegida = grafica?.nombre != null
    CaseElegida = case?.nombre != null
    psuElegida = psu?.nombre != null

    //Desbloquear candado pantalla build
    val unlockMother by viewModell.procesador.observeAsState(true)
    val unlockRAM by viewModell.RAM.observeAsState(true)
    val unlockStorage by viewModell.almacenamiento.observeAsState(true)
    val unlockGrafica by viewModell.tarjeta.observeAsState(true)
    val unlockCase by viewModell.caja.observeAsState(true)
    val unlockpsu by viewModell.psu.observeAsState(true)
    //Bloquear Opciones de remplazar y eliminar
    val lockProcesador by viewModell.processorOptions.observeAsState(false)
    val lockBoard by viewModell.BoardOptions.observeAsState(false)
    val graphicOptions by viewModell.graphicOptions.observeAsState(false)





    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        item {
            Spacer(modifier = Modifier.padding(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 40.dp)
            ) {


                Spacer(modifier = Modifier.padding(12.dp))

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
                        { viewModell.cambiarComponente(0) }, lockProcesador
                    )
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
                        { viewModell.unlockProccesorOptions() },
                        { viewModell.lockRAM() },
                        lockBoard
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                //RAM
                AnimatedVisibility(!RAMelegido) {
                    cargarInformacion(
                        "RAM",
                        !unlockRAM,
                        painterResource(R.drawable.ram),
                        { navigate(2) }
                    ) { viewModell.cambiarComponente(2) }
                }
                if (RAMelegido) {
                    cargarRAM(
                        ram,
                        { viewModell.borrarRAM() },
                        { navigate(2) },
                        { viewModell.cambiarComponente(2) },
                        { viewModell.unlockBoardOptions() },
                        { viewModell.lockStorage() },
                        boardElegida
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                AnimatedVisibility(!Storageelegido) {
                    cargarInformacion(
                        idioma.Almacenamiento,
                        !unlockStorage,
                        painterResource(R.drawable.ssd_),
                        { navigate(3) }
                    ) { viewModell.cambiarComponente(3) }
                }
                if (Storageelegido) {
                    cargarStorage(
                        almacenamiento,
                        { viewModell.borrarAlmacenamiento() },
                        { navigate(3) },
                        { viewModell.cambiarComponente(3) },
                        { viewModell.lockGraphic() },
                        boardElegida
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))

                //Tarjeta grafica
                AnimatedVisibility(!GraphicElegida) {
                    cargarInformacion(
                        idioma.tarjeta,
                        !unlockGrafica,
                        painterResource(R.drawable.graphic),
                        { navigate(4) }
                    ) { viewModell.cambiarComponente(4) }

                }
                if (GraphicElegida) {
                    cargarGrafica(
                        grafica,
                        { viewModell.borrarGrafica() },
                        { navigate(4) },
                        { viewModell.cambiarComponente(4) },
                        { viewModell.lockCase() }, graphicOptions
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))
                //Caja
                AnimatedVisibility(!CaseElegida) {
                    cargarInformacion(
                        idioma.caja,
                        !unlockCase,
                        painterResource(R.drawable.resource_case),
                        { navigate(5) }
                    ) { viewModell.cambiarComponente(5) }
                }

                if (CaseElegida) {
                    cargarCase(
                        case,
                        { viewModell.borrarCaja() },
                        { navigate(5) },
                        { viewModell.cambiarComponente(5) },
                        { viewModell.lockPsu() }, { viewModell.unlockGraphicOptions() })
                }
                Spacer(modifier = Modifier.padding(10.dp))
                AnimatedVisibility(!psuElegida) {
                    cargarInformacion(
                        idioma.fuente,
                        !unlockpsu,
                        painterResource(R.drawable.power),
                        { navigate(6) }
                    ) { viewModell.cambiarComponente(6) }
                }
                if (psuElegida) {
                    cargarPsu(
                        psu,
                        { viewModell.borrarPsu() },
                        { navigate(6) },
                        { viewModell.cambiarComponente(6) })

                }


                Spacer(modifier = Modifier.padding(12.dp))
                var seleccionado by remember { mutableStateOf(false) }

                if (component != null && boardElegida != null && ram != null && almacenamiento != null && grafica != null && case != null && psu != null) {
                    seleccionado = true
                } else {
                    seleccionado = false
                }

                val total =
                    (component?.precio ?: 0.0) + (boardElegida?.precio ?: 0.0) + (ram?.precio
                        ?: 0.0) + (almacenamiento?.precio ?: 0.0) + (grafica?.precio
                        ?: 0.0) + (case?.precio ?: 0.0) + (psu?.precio ?: 0.0)
                Text("Total: $total€", fontSize = 21.sp, fontFamily = Fuentes.mulishSemiBold)
                Spacer(modifier = Modifier.padding(8.dp))
                Row {
                    OutlinedButton(
                        onClick = {},
                        content = { Text("Guardar configuración", fontFamily = Fuentes.mulishSemiBold)  },

                        modifier = Modifier.weight(3f),
                        enabled = seleccionado
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(
                        onClick = { },
                        content = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "", tint = Color.White)},
                        modifier = Modifier.weight(1f),
                        enabled = seleccionado,
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
                    )

                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}


