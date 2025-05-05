package com.example.practicarlogin.principal_screen_navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicarlogin.R
import com.example.practicarlogin.`view-model`.SearchViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.navigation.ComponenteElegido
import com.example.practicarlogin.models.Componente

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Search(screenLista: () -> Unit, searchViewModel: SearchViewModel) {

    Scaffold(
        topBar = { topBarSearch() },
        content = { contentSearch(screenLista, searchViewModel) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarSearch() {
    Column {
        TopAppBar(
            title = {
                Text(
                    "Categorias",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        )
        HorizontalDivider()
    }

}

@Composable
fun contentSearch(screenLista: () -> Unit, searchViewModel: SearchViewModel) {
    Column {
        Spacer(modifier = Modifier.padding(top = 69.dp, start = 15.dp, end = 15.dp, bottom = 15.dp))
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .verticalScroll(scrollState)

        ) {
            val list = listaComponentes()


            Text(
                "Componentes",
                color = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.padding(start = 8.dp, top = 12.dp),
                fontSize = 18.sp,
                fontFamily = Fuentes.mulishBold
            )
            Spacer(modifier = Modifier.padding(5.dp))




            list.forEach { e ->
                OutlinedCard(modifier = Modifier.padding(10.dp), colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.onSecondary)) {
                    Column(
                        modifier = Modifier
                            .clickable { searchViewModel.ElegirComponente(e);screenLista() }
                            .padding(start = 10.dp, end = 10.dp)) {

                        item(componente = e)


                    }
                }


            }
            Text(
                "Periféricos",
                color = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.padding(10.dp),
                fontSize = 18.sp,
                fontFamily = Fuentes.mulishBold
            )
            list.forEach {

                OutlinedCard(modifier = Modifier.padding(10.dp), colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.onSecondary)) {
                    Column(modifier = Modifier.clickable { { screenLista() } }) {

                        item(componente = it)

                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

        }
    }
}

@Composable
fun item(componente: Componente) {

    Spacer(modifier = Modifier.padding(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(componente.image),
            contentDescription = componente.nombre,
            modifier = Modifier.size(50.dp),
            tint = colorResource(R.color.blue)
        )

        Text(
            componente.nombre,
            color = MaterialTheme.colorScheme.inverseSurface,
            fontSize = 18.sp,
            fontFamily = Fuentes.mulishSemiBold
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = colorResource(R.color.blue),
            modifier = Modifier.size(32.dp)
        )
    }
    Spacer(modifier = Modifier.padding(8.dp))

}

fun listaComponentes(): List<Componente> {
    val list = listOf<Componente>(
        Componente(
            nombre = languageSelect.procesador,
            image = R.drawable.proccesor,
            componente = ComponenteElegido.Procesador
        ), Componente(
            nombre = languageSelect.placabase,
            image = R.drawable.motherboard,
            componente = ComponenteElegido.PlacaBase
        ), Componente(
            nombre = "RAM",
            image = R.drawable.ram,
            componente = ComponenteElegido.RAMOrdenador
        ), Componente(
            nombre = languageSelect.Almacenamiento,
            image = R.drawable.ssd_,
            componente = ComponenteElegido.Almacenamiento
        ), Componente(
            nombre = languageSelect.tarjeta,
            image = R.drawable.graphic,
            componente = ComponenteElegido.TarjetaGrafica
        ), Componente(
            nombre = languageSelect.caja,
            image = R.drawable.resource_case,
            componente = ComponenteElegido.CajaOrdenador
        ), Componente(
            nombre = languageSelect.fuente,
            image = R.drawable.power,
            componente = ComponenteElegido.Fuente
        )
    )
    return list
}

fun listaPerifericos(): List<Componente> {
    val list = listOf<Componente>(
        Componente(
            nombre = "Mouse",
            image = R.drawable.mouse,
            componente = ComponenteElegido.Mouse
        ),
        Componente(
            nombre = "Keyboard",
            image = R.drawable.keyboard,
            componente = ComponenteElegido.Keyboard
        ),
        Componente(
            nombre = "Monitor",
            image = R.drawable.monitor,
            componente = ComponenteElegido.Monitor
        ),
        Componente(
            nombre = "Headphones",
            image = R.drawable.headphones,
            componente = ComponenteElegido.HeadPhones
        ),
        Componente(
            nombre = "Mousepad",
            image = R.drawable.mousepad,
            componente = ComponenteElegido.MousePad
        ),
        Componente(
            nombre = "Chair",
            image = R.drawable.chair,
            componente = ComponenteElegido.Chair
        ),
        Componente(
            nombre = "Cable",
            image = R.drawable.mousepad,
            componente = ComponenteElegido.MousePad
        )

    )
    return list

}


