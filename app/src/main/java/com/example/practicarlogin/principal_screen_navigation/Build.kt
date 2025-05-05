package com.example.practicarlogin.principal_screen_navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import com.example.practicarlogin.principal_screen_navigation.CARDS.cargarPsu
import com.example.practicarlogin.principal_screen_navigation.CARDS.cargarRAM
import com.example.practicarlogin.principal_screen_navigation.CARDS.cargarStorage
import com.example.practicarlogin.R
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.models.Board
import com.example.practicarlogin.models.CPU
import com.example.practicarlogin.models.Caja
import com.example.practicarlogin.models.Graphic
import com.example.practicarlogin.models.RAM
import com.example.practicarlogin.models.fuente
import com.example.practicarlogin.models.storage
import com.example.practicarlogin.ui.theme.PracticarLoginTheme
import java.text.NumberFormat
import java.util.Locale

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
fun buildPC(viewModel: ComponentViewModel, navigateTo: (Int) -> Unit) {
    PracticarLoginTheme {
        Scaffold(
            topBar = { topBar() },
            content = { content(viewModel, navigateTo) }
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

    val processor by viewModell.component.observeAsState()
    val boardElegida by viewModell.Boards.observeAsState()
    val ram by viewModell.ram.observeAsState()
    val almacenamiento by viewModell.storagePc.observeAsState()
    val grafica by viewModell.graphic.observeAsState()
    val case by viewModell.chasis.observeAsState()
    val psu by viewModell.psuComponent.observeAsState()
    val unlockMother by viewModell.procesador.observeAsState(true)
    val unlockRAM by viewModell.RamPc.observeAsState(true)
    val unlockStorage by viewModell.almacenamiento.observeAsState(true)
    val unlockGrafica by viewModell.tarjeta.observeAsState(true)
    val unlockCase by viewModell.caja.observeAsState(true)
    val unlockpsu by viewModell.psu.observeAsState(true)
    val lockProcesador by viewModell.processorOptions.observeAsState(false)
    val lockBoard by viewModell.BoardOptions.observeAsState(false)
    val graphicOptions by viewModell.graphicOptions.observeAsState(false)

    RAMelegido = ram?.nombre != null
    cpuElegido = processor?.nombre != null
    boardelegido = boardElegida?.nombre != null
    Storageelegido = almacenamiento?.nombre != null
    GraphicElegida = grafica?.nombre != null
    CaseElegida = case?.nombre != null
    psuElegida = psu?.nombre != null

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
                    .padding(start = 10.dp, end = 10.dp, top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.padding(10.dp))

                LoadProcessorData(navigate, viewModell, processor, lockProcesador)

                LoadBoardData(unlockMother, navigate, viewModell, boardElegida, lockBoard)

                LoadRamData(unlockRAM, navigate, viewModell, ram, boardElegida)

                LoadStorageData(unlockStorage, navigate, viewModell, almacenamiento, boardElegida)

                LoadGraphicData(unlockGrafica, navigate, viewModell, grafica, graphicOptions)

                LoadCaseData(unlockCase, navigate, viewModell, case)

                LoadPSUData(unlockpsu, navigate, viewModell, psu)

                var seleccionado by remember { mutableStateOf(false) }

                seleccionado = isAllSelected(processor, boardElegida, ram, almacenamiento, grafica, case, psu)

                val total = calculatePriceBuild(processor, boardElegida, ram, almacenamiento, grafica, case, psu)

                val price = formatPriceInEuro(total)

                Text("Total: $price", fontSize = 21.sp, fontFamily = Fuentes.mulishSemiBold)
                Spacer(modifier = Modifier.padding(8.dp))
                ButtonsSelected(seleccionado)
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
private fun ButtonsSelected(seleccionado: Boolean) {
    Row {
        OutlinedButton(
            onClick = {},
            content = {
                Text(
                    "Guardar configuración",
                    fontFamily = Fuentes.mulishSemiBold
                )
            },

            modifier = Modifier.weight(3f),
            enabled = seleccionado
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Button(
            onClick = { },
            content = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            modifier = Modifier.weight(1f),
            enabled = seleccionado,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
        )

    }
}

@Composable
private fun isAllSelected(
    processor: CPU?,
    boardElegida: Board?,
    ram: RAM?,
    almacenamiento: storage?,
    grafica: Graphic?,
    case: Caja?,
    psu: fuente?
): Boolean =
    processor != null && boardElegida != null && ram != null && almacenamiento != null && grafica != null && case != null && psu != null

@Composable
private fun ColumnScope.LoadPSUData(
    unlockpsu: Boolean,
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    psu: fuente?
) {
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
}

@Composable
private fun LoadCaseData(
    unlockCase: Boolean,
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    case: Caja?
) {
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
}

@Composable
private fun LoadGraphicData(
    unlockGrafica: Boolean,
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    grafica: Graphic?,
    graphicOptions: Boolean
) {
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
}

@Composable
private fun LoadStorageData(
    unlockStorage: Boolean,
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    almacenamiento: storage?,
    boardElegida: Board?
) {
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
}

@Composable
private fun LoadRamData(
    unlockRAM: Boolean,
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    ram: RAM?,
    boardElegida: Board?
) {
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
}

@Composable
private fun LoadBoardData(
    unlockMother: Boolean,
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    boardElegida: Board?,
    lockBoard: Boolean
) {
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
}

@Composable
private fun LoadProcessorData(
    navigate: (Int) -> Unit,
    viewModell: ComponentViewModel,
    processor: CPU?,
    lockProcesador: Boolean
) {
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
            processor,
            { viewModell.borrarCPU() },
            { navigate(0) },
            { viewModell.cambiarComponente(0) }, lockProcesador
        )
    }
}

private fun formatPriceInEuro(price: Double): String {
    val euroFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    return euroFormat.format(price)
}


private fun calculatePriceBuild(
    component: CPU?,
    boardElegida: Board?,
    ram: RAM?,
    almacenamiento: storage?,
    grafica: Graphic?,
    case: Caja?,
    psu: fuente?
): Double = (component?.precio ?: 0.0) + (boardElegida?.precio ?: 0.0) + (ram?.precio
    ?: 0.0) + (almacenamiento?.precio ?: 0.0) + (grafica?.precio
    ?: 0.0) + (case?.precio ?: 0.0) + (psu?.precio ?: 0.0)


