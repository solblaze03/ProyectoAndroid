package com.example.practicarlogin.search_screen.Productos


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.practicarlogin.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicarlogin.loading_screen.loadingScreen
import com.example.practicarlogin.`view-model`.SearchViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.models.CPU
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun listaCpu(cpu: List<CPU>?, viewModell: SearchViewModel) {


    var marcas = cpu?.map { it.marca }?.distinct()
    var sockets = cpu?.map { it.socket }?.distinct()


    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)



    val filterCpu by viewModell.cpuFilter.observeAsState()
    val marcaSeleccionada by viewModell.marcaSeleccionada.observeAsState("")
    val socketSeleccionado by viewModell.socketSeleccionado.observeAsState("")


    if (cpu != null) {
        //viewModell.initCPU(cpu)
        var busqueda by remember { mutableStateOf("") }
        val filter = remember(busqueda, marcaSeleccionada, socketSeleccionado) {
            cpu.filter { e ->
                (marcaSeleccionada.isEmpty() || e.marca.contains(
                    marcaSeleccionada,
                    ignoreCase = true
                )) &&
                        (socketSeleccionado.isEmpty() || e.socket.contains(
                            socketSeleccionado,
                            ignoreCase = true
                        )) &&
                        (busqueda.isEmpty() || e.nombre.contains(busqueda, ignoreCase = true))
            }
        }
        viewModell.initCPU(filter)

        Column(modifier = Modifier.padding(top = 35.dp)) {

            OutlinedTextField(
                onValueChange = { busqueda = it },
                value = busqueda,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                label = { Text("Buscar Componente") }

            )
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                item {
                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = { showBottomSheet = false },
                            sheetState = sheetState,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    bottom = 0.dp,
                                    start = 20.dp,
                                    end = 20.dp
                                ).fillMaxSize()
                            ) {
                                Text("Filtros", fontFamily = Fuentes.mulishBold, fontSize = 22.sp)
                                Spacer(modifier = Modifier.padding(5.dp))
                                HorizontalDivider()
                                Spacer(modifier = Modifier.padding(12.dp))
                                Text("Ordenar por", fontFamily = Fuentes.mulishSemiBold)
                                Spacer(modifier = Modifier.padding(5.dp))
                                var selectLowToHigh by remember { mutableStateOf(true) }
                                var selectHighToLow by remember { mutableStateOf(false) }
                                Row(modifier = Modifier.fillMaxWidth()) {

                                    FilterChip(
                                        onClick = { if(!selectLowToHigh){selectLowToHigh = !selectLowToHigh; selectHighToLow = false} },
                                        label = { Text("De menor a mayor") },
                                        modifier = Modifier.weight(1f),
                                        selected = selectLowToHigh,
                                        leadingIcon = if (selectLowToHigh) {
                                            {
                                                Icon(
                                                    imageVector = Icons.Filled.Done,
                                                    contentDescription = "Done icon",
                                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                                )
                                            }
                                        } else {
                                            null
                                        })
                                    Spacer(modifier = Modifier.weight(0.1f))
                                    FilterChip(
                                        onClick = { if(!selectHighToLow){selectHighToLow = !selectHighToLow; selectLowToHigh = false} },
                                        label = { Text("De mayor a menor") },
                                        modifier = Modifier.weight(1f),
                                        selected = selectHighToLow ,
                                        leadingIcon = if (selectHighToLow) {
                                            {
                                                Icon(
                                                    imageVector = Icons.Filled.Done,
                                                    contentDescription = "Done icon",
                                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                                )
                                            }
                                        } else {
                                            null
                                        })
                                }
                                Spacer(modifier = Modifier.padding(5.dp))
                                val max = cpu.maxOf { it.precio }.toFloat()
                                val min = cpu.minOf { it.precio }.toFloat()
                                var float by remember { mutableStateOf(min..max+1f) }
                                Text("Precio:", fontFamily = Fuentes.mulishSemiBold)
                                Spacer(modifier = Modifier.padding(5.dp))




                                RangeSlider(
                                    value = float,
                                    onValueChange = { newValue ->
                                        float =
                                            newValue
                                    },
                                    valueRange = min..(max+1f),
                                    steps = max.toInt(),
                                    colors = SliderDefaults.colors(thumbColor = colorResource(R.color.blue))
                                )
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    OutlinedCard(modifier = Modifier.width(150.dp).height(60.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Text(
                                                "€",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            VerticalDivider()
                                            Text(
                                                "${float.start.toInt()}",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }

                                    OutlinedCard(modifier = Modifier.width(150.dp).height(60.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Text(
                                                "€",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            VerticalDivider()
                                            Text(
                                                "${float.endInclusive.toInt()}",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.padding(15.dp))
                                Button(onClick = {
                                    if(selectHighToLow){
                                        viewModell.setOrdenado(false)
                                    }else if(selectLowToHigh){
                                        viewModell.setOrdenado(true)
                                    }else{
                                        viewModell.setOrdenado(null)
                                    }
                                    viewModell.setRango(float)

                                    showBottomSheet = false


                                },content =  { Text("Aplicar cambios")})




                            }
                        }
                    }



                    filtroBotones(R.drawable.filter, { showBottomSheet = true })




                    marcas?.forEach { marca ->
                        var enable = false
                        if (marca.contains(marcaSeleccionada) && (socketSeleccionado.isEmpty() || marcaSeleccionada.isNotEmpty())) {
                            filtroBotonTexto(
                                marca,
                                { viewModell.elegirMarca(marca) },
                                { viewModell.elegirMarca("") },
                            )
                            //

                        }
                    }

                    sockets =
                        cpu?.filter { e -> e.marca.contains(marcaSeleccionada) }?.map { it.socket }
                            ?.distinct()

                    sockets?.forEach { socket ->
                        if (socket.contains(socketSeleccionado)) {
                            filtroBotonTexto(
                                socket,
                                { viewModell.elegirSocket(socket) },
                                { viewModell.elegirSocket("") },

                                )
                        }
                    }

                }
            }

            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 158.dp)) {
                items(filterCpu!!) { e ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(370.dp)
                            .padding(8.dp),
                        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = e.imagen,
                                contentDescription = "",
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(140.dp)
                                    .weight(3f)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.weight(0.2f))
                            Text(
                                "${e.marca} ${e.nombre}/${e.frecuencia}Ghz/${e.socket}",
                                fontFamily = Fuentes.mulishSemiBold,
                                color = MaterialTheme.colorScheme.inverseSurface,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f),
                                fontSize = 16.sp
                            )
                            //Spacer(modifier = Modifier.weight(0.2f))
                            Text(
                                "${e.precio}€",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.inverseSurface,
                                fontFamily = Fuentes.mulishBold
                            )

                            OutlinedButton(
                                onClick = {},
                                content = { Text("Agregar al carrito") },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    } else {
        loadingScreen()
    }
}

@Composable
fun filtroBotones(image: Int, showModalBottom: () -> Unit) {

    OutlinedButton(
        onClick = { showModalBottom() },
        content = {
            Icon(
                imageVector = ImageVector.vectorResource(image),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.size(20.dp)
            )
        },
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun filtroBotonTexto(
    text: String,
    onSelect: () -> Unit,
    onDeselect: () -> Unit

) {
    var selected by remember { mutableStateOf(false) }
    var color = if (selected) Color.Blue else Color.Gray


    OutlinedButton(
        onClick = {
            selected = !selected
            if (selected) onSelect() else onDeselect()
        },
        modifier = Modifier.padding(5.dp)
    ) {
        color = if (text.equals("AMD")) {
            colorResource(R.color.naranja)
        } else {
            colorResource(R.color.azulIntel)
        }
        Text(text, color = if (selected) color else Color.Gray)
    }


}