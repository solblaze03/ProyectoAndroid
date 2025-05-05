package com.example.practicarlogin.Home


import android.R.array
import android.util.Log
import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicarlogin.loading_screen.loadingScreen
import com.example.practicarlogin.R
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.models.CPU
import com.example.practicarlogin.models.Component
import com.example.practicarlogin.models.ComponentDescription
import com.example.practicarlogin.models.Graphic


@Composable
fun PrincipalHome(componentViewModel: ComponentViewModel) {


    val processors by componentViewModel.cpus.observeAsState(emptyList())
    val graphics by componentViewModel.graphiclist.observeAsState(emptyList())
    val components by componentViewModel.componentsDescription.observeAsState(mutableListOf())
    val componentNameSelected by componentViewModel.componentNameSelected.observeAsState("")
    val componentsWithLimit by componentViewModel.componentsWithLimit.observeAsState(mutableListOf())

    val list by remember { mutableStateOf(groupComponents(componentsWithLimit)) }






    LaunchedEffect(Unit) {
        componentViewModel.getCPUWithLimit(10);
    }
    LaunchedEffect(Unit) {
        componentViewModel.getGraphicsByPower(10)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, top = 30.dp)
    ) {


        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            item {
                Text(
                    text = "Bienvenido de nuevo \uD83D\uDC4B",
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontFamily = Fuentes.mulishBold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.padding(12.dp))

                News()

                Spacer(modifier = Modifier.padding(18.dp))

                PopularProcessors(modifier = Modifier, processors)

                PopularGraphics(modifier = Modifier, graphics)

                Spacer(modifier = Modifier.padding(12.dp))

                BestOfThatBrand(componentNameSelected, components)

                VerticalGridComponents(list)

                Spacer(modifier = Modifier.padding(20.dp))
            }


        }


    }


}

@Composable
fun News() {
    Subtitle(text = "Novedades")
    Spacer(modifier = Modifier.padding(5.dp))
    Image(
        painter = painterResource(R.drawable.novedad),
        contentDescription = null,
        modifier = Modifier.clip(RoundedCornerShape(11.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PopularProcessors(modifier: Modifier = Modifier, processors: List<CPU>) {
    Subtitle("Procesadores populares")
    Spacer(modifier = Modifier.padding(8.dp))
    if (processors.isNotEmpty()) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(21.dp)) {
            items(processors) { processor ->
                CardComponent(
                    modifier = Modifier
                        .width(130.dp)
                        .height(210.dp),
                    processor
                )
            }
        }
    } else {
        loadingScreen()
    }
}

@Composable
fun CardComponent(modifier: Modifier = Modifier,componentDescription: ComponentDescription ) {

    Surface(
        modifier = modifier,
        onClick = {}
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageCard(componentDescription.image)
            Spacer(modifier = Modifier.padding(5.dp))
            DescriptionComponent(componentDescription.nombre, componentDescription.precio)

        }
    }
}


@Composable
fun CardComponent(modifier: Modifier = Modifier,graphic: Graphic ) {

    Surface(
        modifier = modifier,
        onClick = {}
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageCard(graphic.imagen)
            Spacer(modifier = Modifier.padding(5.dp))
            DescriptionComponent(graphic.nombre, graphic.precio)

        }
    }
}

@Composable
fun CardComponent(modifier: Modifier = Modifier,Cpu: CPU ) {

    Surface(
        modifier = modifier,
        onClick = {}
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageCard(Cpu.imagen)
            Spacer(modifier = Modifier.padding(5.dp))
            DescriptionComponent(Cpu.nombre, Cpu.precio)

        }
    }
}

@Composable
private fun DescriptionComponent(title: String, price: Double) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.inverseSurface,
        fontFamily = Fuentes.mulishRegular,
        fontSize = 14.sp
    )

    Text(
        text = "${price}€",
        textAlign = TextAlign.Right,
        color = MaterialTheme.colorScheme.inverseSurface,
        fontFamily = Fuentes.mulishBold,
        fontSize = 14.sp
    )
}

@Composable
private fun ImageCard(image: String) {
    AsyncImage(
        model = image,
        contentDescription = null,
        modifier = Modifier
            .width(150.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                0.5.dp,
                MaterialTheme.colorScheme.inverseSurface,
                shape = RoundedCornerShape(10.dp)
            ),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun Subtitle(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.inverseSurface,
        fontFamily = Fuentes.mulishRegular,
        fontSize = 18.sp
    )
}

@Composable
fun PopularGraphics(modifier: Modifier = Modifier, graphics: List<Graphic>) {
    Subtitle("Mejores Graficas")

    Spacer(modifier = modifier.padding(5.dp))
    if (graphics.isNotEmpty()) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(21.dp)) {
            items(graphics) { graphic ->
                CardComponent(
                    modifier = Modifier
                        .width(130.dp)
                        .height(210.dp),
                    graphic
                )
            }
        }

    } else {
        loadingScreen()
    }
}

@Composable
fun BestOfThatBrand(brand: String, items: MutableList<ComponentDescription>) {
    if (items.isNotEmpty()) {
        Subtitle("Lo mejor de $brand")
        Spacer(modifier = Modifier.padding(5.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(21.dp)) {
            items(items) { component ->
                CardComponent(
                    modifier = Modifier
                        .width(130.dp)
                        .height(210.dp),
                    ComponentDescription(
                        image = component.image,
                        nombre = component.nombre,
                        precio = component.precio,
                        component = component.component
                    )
                )
            }
        }
    }
}


@Composable
fun VerticalGridComponents(componentsWithLimit: List<List<ComponentDescription>>) {

    Subtitle("Otros componentes")
    Spacer(modifier = Modifier.padding(5.dp))
    Column(modifier = Modifier.fillMaxWidth(),
     ) {

        componentsWithLimit.forEach { chunkedComponent ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                for (component in chunkedComponent) {
                    CardComponent(
                        modifier = Modifier
                            .width(160.dp)
                            .height(210.dp),
                        ComponentDescription(
                            image = component.image,
                            nombre = component.nombre,
                            precio = component.precio,
                            component = component.component
                        )

                    )
                }

            }
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }


}

fun groupComponents(componentsWithLimit: MutableList<ComponentDescription>): List<List<ComponentDescription>> {
    val list: List<List<ComponentDescription>> = componentsWithLimit.chunked(2)
    return list
}